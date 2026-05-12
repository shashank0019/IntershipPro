package com.karunadavanya.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karunadavanya.R
import com.karunadavanya.ui.main.MainActivity
import com.karunadavanya.viewmodel.AuthViewModel

class AuthActivity : AppCompatActivity(), AuthNavigationHost {

    private val authViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (authViewModel.isLoggedIn()) {
            openMainScreen()
            return
        }

        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.authContainer, LoginFragment())
                .commit()
        }
    }

    override fun showSignup() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authContainer, SignupFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showLogin() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.authContainer, LoginFragment())
            .commit()
    }

    override fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
