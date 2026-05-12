package com.karunadavanya.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.karunadavanya.R
import com.karunadavanya.viewmodel.AuthState
import com.karunadavanya.viewmodel.AuthViewModel

class LoginFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        val emailInput = view.findViewById<EditText>(R.id.emailInput)
        val passwordInput = view.findViewById<EditText>(R.id.passwordInput)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val goToSignupText = view.findViewById<TextView>(R.id.goToSignupText)

        loginButton.setOnClickListener {
            authViewModel.login(
                emailInput.text.toString().trim(),
                passwordInput.text.toString().trim()
            )
        }

        goToSignupText.setOnClickListener {
            (activity as? AuthNavigationHost)?.showSignup()
        }

        authViewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                AuthState.Success -> {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    (activity as? AuthNavigationHost)?.openMainScreen()
                }
                is AuthState.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
    }
}
