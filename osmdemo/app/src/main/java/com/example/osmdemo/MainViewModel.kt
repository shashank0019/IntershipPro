package com.example.osmdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = LocationRepository()

    private val _markers = MutableLiveData<List<MarkerData>>(emptyList())
    val markers: LiveData<List<MarkerData>> = _markers

    fun startListeningMarkers() {
        repository.listenForMarkers { list ->
            _markers.postValue(list)
        }
    }

    fun addMarker(lat: Double, lng: Double, label: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveMarker(MarkerData(lat = lat, lng = lng, label = label))
        }
    }
}
