package com.karunadavanya.ui.home

import android.os.Bundle
import android.view.MotionEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.karunadavanya.R
import com.karunadavanya.viewmodel.AlertsViewModel
import com.karunadavanya.viewmodel.AuthViewModel
import com.karunadavanya.viewmodel.EducationViewModel

class HomeFragment : Fragment() {

    private lateinit var alertsViewModel: AlertsViewModel
    private lateinit var educationViewModel: EducationViewModel
    private lateinit var authViewModel: AuthViewModel
    private lateinit var featuredAdapter: FeaturedWildlifeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        alertsViewModel = ViewModelProvider(requireActivity())[AlertsViewModel::class.java]
        educationViewModel = ViewModelProvider(requireActivity())[EducationViewModel::class.java]
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        
        featuredAdapter = FeaturedWildlifeAdapter()

        // Profile display
        val profileSection = view.findViewById<View>(R.id.userProfileSection)
        val userNameText = view.findViewById<TextView>(R.id.userNameText)
        val userPhoneText = view.findViewById<TextView>(R.id.userPhoneText)
        val userEmailText = view.findViewById<TextView>(R.id.userEmailText)

        authViewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                profileSection.visibility = View.VISIBLE
                userNameText.text = profile.name
                userPhoneText.text = profile.phone
                userEmailText.text = profile.email
            }
        }
        authViewModel.fetchUserProfile()

        // Logout listener
        view.findViewById<View>(R.id.fragmentLogoutButton).setOnClickListener {
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

        val featuredRecycler = view.findViewById<RecyclerView>(R.id.featuredWildlifeRecycler)
        featuredRecycler.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        featuredRecycler.isNestedScrollingEnabled = true
        featuredRecycler.setHasFixedSize(true)
        featuredRecycler.adapter = featuredAdapter
        LinearSnapHelper().attachToRecyclerView(featuredRecycler)
        // Removed manual touch listener to allow default nested scrolling behavior

        val alertStatusText = view.findViewById<TextView>(R.id.alertStatusText)

        view.findViewById<MaterialCardView>(R.id.quickEducationButton).setOnClickListener {
            (activity as? com.karunadavanya.ui.main.MainActivity)?.navigateTo(R.id.nav_education)
        }

        view.findViewById<MaterialCardView>(R.id.quickAlertButton).setOnClickListener {
            (activity as? com.karunadavanya.ui.main.MainActivity)?.navigateTo(R.id.nav_alerts)
        }

        alertsViewModel.alerts.observe(viewLifecycleOwner) { alerts ->
            val count = alerts.size
            alertStatusText.text = "$count active alerts nearby"
        }

        educationViewModel.animals.observe(viewLifecycleOwner) { animals ->
            featuredAdapter.submitList(animals)
        }

        educationViewModel.loadAnimals(requireContext())
        alertsViewModel.startListening()
    }
}
