package com.karunadavanya.ui.alerts

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.karunadavanya.R
import com.karunadavanya.viewmodel.AlertsViewModel
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class AlertsFragment : Fragment() {

    private lateinit var viewModel: AlertsViewModel
    private lateinit var adapter: AlertsAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Current open dialog reference
    private var currentDialog: AlertDialog? = null

    // Dialog-scoped state
    private var selectedLocationText: String? = null
    private var selectedImageUri: Uri? = null

    // Dialog view references
    private var dlgLocationStatusText: TextView? = null
    private var dlgLocationSubtitleText: TextView? = null
    private var dlgLocationDetectedRow: LinearLayout? = null
    private var dlgDetectedAddressText: TextView? = null
    private var dlgSelectLocationButton: MaterialButton? = null
    private var dlgChoosePhotoButton: MaterialButton? = null
    private var dlgPhotoPreviewCard: MaterialCardView? = null
    private var dlgPhotoPreviewImage: ImageView? = null
    private var dlgSendButton: MaterialButton? = null

    /* ── Activity Result Launchers ───────────────────────────────── */
    
    private val pickLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val data = result.data
            val address = data?.getStringExtra("address")
            if (!address.isNullOrEmpty()) {
                selectedLocationText = address
                showLocationSuccess(address)
            }
        }
        setLocationButtonLoading(false, "Use GPS")
    }

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val uri = result.data?.data ?: return@registerForActivityResult
            handleImageSelection(uri)
        }
    }

    /* ── Lifecycle ─────────────────────────────────────────────────── */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_alerts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity())[AlertsViewModel::class.java]
        adapter = AlertsAdapter()

        view.findViewById<RecyclerView>(R.id.alertsRecycler).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@AlertsFragment.adapter
        }

        view.findViewById<RecyclerView>(R.id.alertsRecycler).isNestedScrollingEnabled = true

        view.findViewById<ExtendedFloatingActionButton>(R.id.reportFab)
            .setOnClickListener { openReportDialog() }

        viewModel.alerts.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.statusMessage.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty())
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
        viewModel.startListening()
    }

    /* ── Dialog ────────────────────────────────────────────────────── */
    private fun openReportDialog() {
        selectedLocationText = null
        selectedImageUri = null

        val dialogView = layoutInflater.inflate(R.layout.dialog_report_alert, null)
        val animalSpinner    = dialogView.findViewById<Spinner>(R.id.animalSpinner)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.descriptionInput)

        dlgSendButton            = dialogView.findViewById(R.id.sendAlertButton)
        dlgSelectLocationButton  = dialogView.findViewById(R.id.selectLocationButton)
        dlgLocationStatusText    = dialogView.findViewById(R.id.locationStatusText)
        dlgLocationSubtitleText  = dialogView.findViewById(R.id.locationSubtitleText)
        dlgLocationDetectedRow   = dialogView.findViewById(R.id.locationDetectedRow)
        dlgDetectedAddressText   = dialogView.findViewById(R.id.detectedAddressText)
        dlgChoosePhotoButton     = dialogView.findViewById(R.id.choosePhotoButton)
        dlgPhotoPreviewCard      = dialogView.findViewById(R.id.photoPreviewCard)
        dlgPhotoPreviewImage     = dialogView.findViewById(R.id.photoPreviewImage)
        
        dlgChoosePhotoButton?.text = "Choose Photo"
        dlgPhotoPreviewCard?.visibility = View.GONE

        val dialog = AlertDialog.Builder(requireContext(), R.style.CustomDialogTheme)
            .setView(dialogView)
            .create()
        currentDialog = dialog

        dlgSelectLocationButton?.setOnClickListener {
            val it = Intent(requireContext(), com.karunadavanya.ui.location.LocationPickerActivity::class.java)
            setLocationButtonLoading(true, "Opening map…")
            pickLocationLauncher.launch(it)
        }

        dlgChoosePhotoButton?.setOnClickListener {
            Toast.makeText(requireContext(), "Click the ☰ menu and select 'Downloads' to find laptop photos", Toast.LENGTH_LONG).show()
            openGallery()
        }

        dlgSendButton?.setOnClickListener {
            val animal      = animalSpinner.selectedItem?.toString() ?: ""
            val description = descriptionInput.text.toString().trim()
            if (animal.isEmpty()) {
                Toast.makeText(requireContext(), "Please select an animal", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendAlert(animal, description)
        }

        dialog.show()
        dialog.window?.let { w ->
            w.setLayout(
                (resources.displayMetrics.widthPixels * 0.92).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun openGallery() {
        // ACTION_GET_CONTENT is the most compatible way to browse "Downloads" 
        // where laptop-dragged files are stored.
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        pickImageLauncher.launch(intent)
    }

    private fun handleImageSelection(uri: Uri) {
        selectedImageUri = uri
        dlgPhotoPreviewImage?.setImageURI(uri)
        dlgPhotoPreviewCard?.visibility = View.VISIBLE
        dlgChoosePhotoButton?.text = "Change Photo"
    }

    /* ── Send logic ────────────────────────────────────────────────── */
    private fun sendAlert(animal: String, description: String) {
        dlgSendButton?.isEnabled = false
        dlgSendButton?.text = "Sending…"

        val uri = selectedImageUri
        if (uri != null) {
            viewModel.reportAlertWithImage(
                animalType   = animal,
                description  = description,
                locationText = selectedLocationText,
                imageUri     = uri,
                onProgress   = { pct ->
                    requireActivity().runOnUiThread {
                        dlgSendButton?.text = "Uploading $pct%…"
                    }
                },
                onDone = { success ->
                    requireActivity().runOnUiThread {
                        if (success) {
                            currentDialog?.dismiss()
                        } else {
                            dlgSendButton?.isEnabled = true
                            dlgSendButton?.text = "Send Alert"
                        }
                    }
                }
            )
        } else {
            viewModel.reportAlert(animal, description, selectedLocationText)
            currentDialog?.dismiss()
        }
    }

    /* ── Location Helpers ────────────────────────────────────────── */
    
    private fun showLocationSuccess(address: String) {
        dlgLocationStatusText?.text  = "Location Detected ✓"
        dlgLocationSubtitleText?.text = "Resolved address"
        dlgDetectedAddressText?.text  = address
        dlgLocationDetectedRow?.visibility = View.VISIBLE
        setLocationButtonLoading(false, label = "Change Location")
    }

    private fun setLocationButtonLoading(loading: Boolean, label: String = "Use GPS") {
        dlgSelectLocationButton?.apply {
            isEnabled = !loading
            text = if (loading) "Processing…" else label
        }
    }
}
