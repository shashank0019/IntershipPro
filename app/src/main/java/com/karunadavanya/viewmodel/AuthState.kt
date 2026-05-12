package com.karunadavanya.viewmodel

sealed class AuthState {
    data object Idle : AuthState()
    data object Success : AuthState()
    data object LoggedOut : AuthState()
    data class Error(val message: String) : AuthState()
}
