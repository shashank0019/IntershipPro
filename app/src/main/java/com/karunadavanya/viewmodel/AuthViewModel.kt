package com.karunadavanya.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karunadavanya.data.model.UserProfile
import com.karunadavanya.data.repository.FirebaseRepository

class AuthViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Please fill all required fields")
            return
        }

        repository.login(email, password,
            onSuccess = { _authState.value = AuthState.Success },
            onError = { _authState.value = AuthState.Error(it) }
        )
    }

    fun signUp(email: String, password: String, name: String, phone: String) {
        if (email.isBlank() || password.isBlank() || name.isBlank() || phone.isBlank()) {
            _authState.value = AuthState.Error("Please fill all required fields")
            return
        }

        repository.register(email, password, name, phone,
            onSuccess = { _authState.value = AuthState.Success },
            onError = { _authState.value = AuthState.Error(it) }
        )
    }

    fun fetchUserProfile() {
        repository.getUserProfile(
            onSuccess = { _userProfile.postValue(it) },
            onError = { /* Handle error if needed */ }
        )
    }

    fun logout() {
        repository.logout()
        _userProfile.value = null
        _authState.value = AuthState.LoggedOut
    }

    fun isLoggedIn(): Boolean = repository.isUserLoggedIn()

    fun currentUserEmail(): String = repository.currentUserEmail()
}
