package com.example.osmdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MainActivity : AppCompatActivity() {

    private lateinit var map: MapView
    private lateinit var viewModel: MainViewModel
    private var myLocationOverlay: MyLocationNewOverlay? = null

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val granted = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true || perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) setupLocation() else Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // osmdroid configuration (use default cache dir)
        Configuration.getInstance().load(applicationContext, androidx.preference.PreferenceManager.getDefaultSharedPreferences(applicationContext))

        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        map = findViewById(R.id.mapView)
        map.setMultiTouchControls(true)
        map.controller.setZoom(12.0)

        // show zoom controls
        map.zoomController.setVisibility(org.osmdroid.views.zoom.ZoomButtonsController.Visibility.SHOW_AND_FADEOUT)

        // observe markers from ViewModel
        viewModel.markers.observe(this) { list ->
            map.overlays.clear()
            myLocationOverlay?.let { map.overlays.add(it) }
            list.forEach { d ->
                val marker = Marker(map)
                marker.position = GeoPoint(d.lat, d.lng)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = d.label ?: "Marker"
                map.overlays.add(marker)
            }
            map.invalidate()
        }

        // fab to add marker at center and save to Firebase
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            val center = map.mapCenter as GeoPoint
            viewModel.addMarker(center.latitude, center.longitude, "Saved marker")
        }

        // request permissions
        ensurePermissions()

        // load initial markers
        viewModel.startListeningMarkers()
    }

    private fun ensurePermissions() {
        val needed = mutableListOf<String>()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            needed.add(Manifest.permission.ACCESS_FINE_LOCATION)
            needed.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (needed.isNotEmpty()) permissionLauncher.launch(needed.toTypedArray()) else setupLocation()
    }

    private fun setupLocation() {
        // MyLocation overlay using GPS provider
        val provider = GpsMyLocationProvider(this)
        myLocationOverlay = MyLocationNewOverlay(provider, map)
        myLocationOverlay?.enableMyLocation()
        myLocationOverlay?.enableFollowLocation()
        map.overlays.add(myLocationOverlay)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}
