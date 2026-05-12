package com.example.osmdemo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LocationRepository {

    private val db = FirebaseDatabase.getInstance()
    private val markersRef = db.getReference("markers")

    fun saveMarker(marker: MarkerData) {
        val key = markersRef.push().key ?: return
        markersRef.child(key).setValue(marker)
    }

    fun listenForMarkers(onUpdate: (List<MarkerData>) -> Unit) {
        markersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<MarkerData>()
                for (c in snapshot.children) {
                    val m = c.getValue(MarkerData::class.java)
                    if (m != null) list.add(m)
                }
                onUpdate(list)
            }

            override fun onCancelled(error: DatabaseError) {
                // ignore for now
            }
        })
    }
}
