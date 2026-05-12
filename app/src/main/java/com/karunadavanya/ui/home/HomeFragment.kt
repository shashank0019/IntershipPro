package com.karunadavanya.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.karunadavanya.R
import com.karunadavanya.databinding.FragmentHomeBinding
import com.karunadavanya.viewmodel.AlertsViewModel
import com.karunadavanya.viewmodel.AuthViewModel
import com.karunadavanya.viewmodel.EducationViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var alertsViewModel: AlertsViewModel
    private lateinit var educationViewModel: EducationViewModel
    private lateinit var authViewModel: AuthViewModel
    private lateinit var featuredAdapter: FeaturedWildlifeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        alertsViewModel = ViewModelProvider(requireActivity())[AlertsViewModel::class.java]
        educationViewModel = ViewModelProvider(requireActivity())[EducationViewModel::class.java]
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        
        featuredAdapter = FeaturedWildlifeAdapter()

        // Profile button click listener
        binding.profileIconButton.setOnClickListener {
            showProfileDialog()
        }

        // Logout listener
        binding.fragmentLogoutButton.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Log Out") { _, _ ->
                    authViewModel.logout()
                    val intent = android.content.Intent(requireContext(), com.karunadavanya.ui.auth.AuthActivity::class.java)
                    intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        // Setup RecyclerView
        binding.featuredWildlifeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            adapter = featuredAdapter
            LinearSnapHelper().attachToRecyclerView(this)
        }

        binding.quickEducationButton.setOnClickListener {
            (activity as? com.karunadavanya.ui.main.MainActivity)?.navigateTo(R.id.nav_education)
        }

        binding.quickAlertButton.setOnClickListener {
            (activity as? com.karunadavanya.ui.main.MainActivity)?.navigateTo(R.id.nav_alerts)
        }

        // Observers
        alertsViewModel.alerts.observe(viewLifecycleOwner) { alerts ->
            binding.alertStatusText.text = "${alerts.size} active alerts nearby"
        }

        educationViewModel.animals.observe(viewLifecycleOwner) { animals ->
            featuredAdapter.submitList(animals)
        }

        authViewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                binding.userProfileSection.visibility = View.VISIBLE
                binding.userNameText.text = profile.name
                binding.userPhoneText.text = profile.phone
                binding.userEmailText.text = profile.email
            } else {
                binding.userProfileSection.visibility = View.GONE
            }
        }

        educationViewModel.loadAnimals(requireContext())
        alertsViewModel.startListening()
        authViewModel.fetchUserProfile()
    }

    private fun showProfileDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_user_profile, null)
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val nameTxt = dialogView.findViewById<TextView>(R.id.dlgUserName)
        val phoneTxt = dialogView.findViewById<TextView>(R.id.dlgUserPhone)
        val emailTxt = dialogView.findViewById<TextView>(R.id.dlgUserEmail)
        val closeBtn = dialogView.findViewById<View>(R.id.dlgCloseBtn)

        authViewModel.userProfile.value?.let { profile ->
            nameTxt.text = profile.name
            phoneTxt.text = profile.phone
            emailTxt.text = profile.email
        }

        closeBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
