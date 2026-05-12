package com.karunadavanya.data.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.karunadavanya.data.model.UserProfile
import com.karunadavanya.data.model.WildlifeAlert

class FirebaseRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid.orEmpty()
                val profile = UserProfile(uid, name, phone, email)
                saveUserProfile(profile, onSuccess, onError)
            }
            .addOnFailureListener { onError(it.message ?: "Signup failed") }
    }

    private fun saveUserProfile(
        profile: UserProfile,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        firestore.collection("users").document(profile.uid)
            .set(profile)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Failed to save profile") }
    }

    fun getUserProfile(onSuccess: (UserProfile) -> Unit, onError: (String) -> Unit) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { snapshot ->
                val profile = snapshot.toObject(UserProfile::class.java)
                if (profile != null) onSuccess(profile)
                else onError("Profile not found")
            }
            .addOnFailureListener { onError(it.message ?: "Failed to fetch profile") }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Login failed") }
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUserEmail(): String {
        return auth.currentUser?.email.orEmpty()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun addAlert(
        alert: WildlifeAlert,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val payload = hashMapOf(
            "animalType" to alert.animalType,
            "description" to alert.description,
            "timestampMillis" to alert.timestampMillis,
            "imageUrl" to alert.imageUrl,
            "locationText" to alert.locationText,
            "reporterEmail" to alert.reporterEmail
        )

        firestore.collection("wildlife_alerts")
            .add(payload)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Failed to report alert") }
    }

    fun listenForAlerts(onUpdate: (List<WildlifeAlert>) -> Unit) {
        firestore.collection("wildlife_alerts")
            .orderBy("timestampMillis", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (snapshot == null || error != null) {
                    onUpdate(emptyList())
                    return@addSnapshotListener
                }

                val alerts = snapshot.documents.map { document ->
                    WildlifeAlert(
                        id = document.id,
                        animalType = document.getString("animalType").orEmpty(),
                        description = document.getString("description").orEmpty(),
                        timestampMillis = document.getLong("timestampMillis") ?: 0L,
                        imageUrl = document.getString("imageUrl"),
                        locationText = document.getString("locationText"),
                        reporterEmail = document.getString("reporterEmail").orEmpty()
                    )
                }
                onUpdate(alerts)
            }
    }

    fun cleanupExpiredAlerts(onFinished: (() -> Unit)? = null) {
        val threshold = System.currentTimeMillis() - 6 * 60 * 60 * 1000
        firestore.collection("wildlife_alerts")
            .whereLessThan("timestampMillis", threshold)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val batch = firestore.batch()
                querySnapshot.documents.forEach { batch.delete(it.reference) }
                batch.commit().addOnCompleteListener { onFinished?.invoke() }
            }
            .addOnFailureListener { onFinished?.invoke() }
    }

    fun testFirestoreConnection(onResult: (Boolean, String) -> Unit) {
        val now = System.currentTimeMillis()
        val docRef = firestore.collection("connection_tests").document("latest")

        val payload = hashMapOf(
            "timestampMillis" to now,
            "tester" to currentUserEmail().ifBlank { "anonymous" }
        )

        docRef.set(payload)
            .addOnSuccessListener {
                docRef.get()
                    .addOnSuccessListener { snapshot ->
                        val serverTime = snapshot.getLong("timestampMillis") ?: 0L
                        if (snapshot.exists() && serverTime > 0L) {
                            onResult(true, "Firebase connected. Last test: ${serverTime}")
                        } else {
                            onResult(false, "Firebase test failed: invalid response")
                        }
                    }
                    .addOnFailureListener {
                        onResult(false, it.message ?: "Firebase read test failed")
                    }
            }
            .addOnFailureListener {
                onResult(false, it.message ?: "Firebase write test failed")
            }
    }

    fun uploadImageAndAddAlert(
        imageUri: Uri,
        alert: WildlifeAlert,
        onProgress: (Int) -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val storageRef = storage.reference
            .child("alert_images/${System.currentTimeMillis()}.jpg")

        storageRef.putFile(imageUri)
            .addOnProgressListener { task ->
                val pct = (100.0 * task.bytesTransferred / task.totalByteCount).toInt()
                onProgress(pct)
            }
            .addOnSuccessListener {
                storageRef.downloadUrl
                    .addOnSuccessListener { downloadUri ->
                        addAlert(
                            alert.copy(imageUrl = downloadUri.toString()),
                            onSuccess,
                            onError
                        )
                    }
                    .addOnFailureListener { e ->
                        onError(e.message ?: "Failed to get download URL")
                    }
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Image upload failed")
            }
    }
}
