package com.karunadavanya.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karunadavanya.R
import java.util.Locale

/**
 * Location picker using Google Maps.
 */
class LocationPickerActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null
    private lateinit var tvAddress: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var selectedPoint: LatLng? = null

    private val handler = Handler(Looper.getMainLooper())
    private var reverseRunnable: Runnable? = null

    private val permLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val fine = perms[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarse = perms[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        if (fine || coarse) {
            fetchCurrentLocation()
        } else {
            Toast.makeText(this, "Location permission required", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_picker)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        tvAddress = findViewById(R.id.tvAddress)
        mapView = findViewById(R.id.pickerMap)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        findViewById<Button>(R.id.btnUseGps).setOnClickListener {
            checkPermissionsAndFetch()
        }

        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            confirmSelection()
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        
        // Setup initial camera position
        val startPoint = LatLng(12.9716, 77.5946) // Bangalore
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 15f))
        updateSelectedPoint(startPoint, false)

        googleMap?.setOnCameraMoveListener {
            val center = googleMap?.cameraPosition?.target
            center?.let { updateSelectedPoint(it, false) }
        }

        googleMap?.setOnMapClickListener { point ->
            updateSelectedPoint(point, true)
        }
    }

    private fun checkPermissionsAndFetch() {
        val fine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        
        if (fine == PackageManager.PERMISSION_GRANTED || coarse == PackageManager.PERMISSION_GRANTED) {
            fetchCurrentLocation()
        } else {
            permLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }

    @SuppressLint("MissingPermission")
    private fun fetchCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val point = LatLng(location.latitude, location.longitude)
                updateSelectedPoint(point, true)
            } else {
                Toast.makeText(this, "Unable to get location. Is GPS on?", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateSelectedPoint(point: LatLng, moveCamera: Boolean) {
        selectedPoint = point
        
        googleMap?.clear()
        googleMap?.addMarker(MarkerOptions().position(point))
        
        if (moveCamera) {
            googleMap?.animateCamera(CameraUpdateFactory.newLatLng(point))
        }
        
        scheduleReverseGeocode(point)
    }

    private fun scheduleReverseGeocode(point: LatLng) {
        reverseRunnable?.let { handler.removeCallbacks(it) }
        reverseRunnable = Runnable {
            resolveAddress(point.latitude, point.longitude)
        }
        handler.postDelayed(reverseRunnable!!, 800)
    }

    private fun resolveAddress(lat: Double, lng: Double) {
        Thread {
            val address = try {
                val geocoder = Geocoder(this, Locale.getDefault())
                val list = geocoder.getFromLocation(lat, lng, 1)
                if (!list.isNullOrEmpty()) {
                    list[0].getAddressLine(0)
                } else {
                    "%.5f, %.5f".format(lat, lng)
                }
            } catch (e: Exception) {
                "%.5f, %.5f".format(lat, lng)
            }
            runOnUiThread {
                tvAddress.text = address
            }
        }.start()
    }

    private fun confirmSelection() {
        val point = selectedPoint ?: googleMap?.cameraPosition?.target ?: LatLng(0.0, 0.0)
        val address = tvAddress.text.toString()
        
        val intent = Intent().apply {
            putExtra("lat", point.latitude)
            putExtra("lng", point.longitude)
            putExtra("address", address)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
