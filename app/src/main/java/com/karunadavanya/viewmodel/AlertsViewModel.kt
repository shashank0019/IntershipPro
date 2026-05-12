package com.karunadavanya.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karunadavanya.data.model.WildlifeAlert
import com.karunadavanya.data.repository.FirebaseRepository

class AlertsViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    private val _alerts = MutableLiveData<List<WildlifeAlert>>()
    val alerts: LiveData<List<WildlifeAlert>> = _alerts
    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage
    private val _firebaseStatus = MutableLiveData<String>()
    val firebaseStatus: LiveData<String> = _firebaseStatus

    fun startListening() {
        repository.cleanupExpiredAlerts()
        repository.listenForAlerts { _alerts.postValue(it) }
        verifyFirebaseConnection()
    }

    /** Report an alert with no image attachment. */
    fun reportAlert(
        animalType: String,
        description: String,
        locationText: String?
    ) {
        if (animalType.isBlank()) {
            _statusMessage.value = "Please choose an animal type"
            return
        }
        val alert = WildlifeAlert(
            animalType = animalType,
            description = description,
            timestampMillis = System.currentTimeMillis(),
            locationText = locationText,
            reporterEmail = repository.currentUserEmail()
        )
        repository.addAlert(
            alert = alert,
            onSuccess = { _statusMessage.postValue("Alert reported successfully!") },
            onError   = { _statusMessage.postValue(it) }
        )
    }

    /**
     * Upload [imageUri] to Firebase Storage, then save the alert with the
     * resulting download URL.  [onProgress] receives 0-100 values.
     * [onDone] is called on both success and error so the caller can re-enable UI.
     */
    fun reportAlertWithImage(
        animalType: String,
        description: String,
        locationText: String?,
        imageUri: Uri,
        onProgress: (Int) -> Unit,
        onDone: (success: Boolean) -> Unit
    ) {
        if (animalType.isBlank()) {
            _statusMessage.value = "Please choose an animal type"
            onDone(false)
            return
        }
        val alert = WildlifeAlert(
            animalType    = animalType,
            description   = description,
            timestampMillis = System.currentTimeMillis(),
            locationText  = locationText,
            reporterEmail = repository.currentUserEmail()
        )
        repository.uploadImageAndAddAlert(
            imageUri   = imageUri,
            alert      = alert,
            onProgress = onProgress,
            onSuccess  = {
                _statusMessage.postValue("Alert reported successfully!")
                onDone(true)
            },
            onError    = { err ->
                _statusMessage.postValue(err)
                onDone(false)
            }
        )
    }

    fun verifyFirebaseConnection() {
        _firebaseStatus.value = "Checking Firebase connection..."
        repository.testFirestoreConnection { connected, message ->
            _firebaseStatus.postValue(
                if (connected) "Status: Connected\n$message"
                else "Status: Not connected\n$message"
            )
        }
    }
}
