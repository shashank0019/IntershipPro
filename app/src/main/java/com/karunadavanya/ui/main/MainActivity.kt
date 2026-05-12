package com.karunadavanya.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.karunadavanya.R
import com.karunadavanya.ui.alerts.AlertsFragment
import com.karunadavanya.ui.audio.SoundsFragment
import com.karunadavanya.ui.auth.AuthActivity
import com.karunadavanya.ui.education.EducationFragment
import com.karunadavanya.ui.guide.GuideFragment
import com.karunadavanya.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        if (savedInstanceState == null) {
            openFragment(HomeFragment())
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home      -> openFragment(HomeFragment())
                R.id.nav_education -> openFragment(EducationFragment())
                R.id.nav_alerts    -> openFragment(AlertsFragment())
                R.id.nav_sounds    -> openFragment(SoundsFragment())
                R.id.nav_guide     -> openFragment(GuideFragment())
            }
            true
        }
    }

    fun navigateTo(tabId: Int) {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId = tabId
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
