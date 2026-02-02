package com.riteshapps.learnhub.presentation.auth

sealed class AuthUiEvent {
    data class UsernameChanged(val value: String) : AuthUiEvent()
    data class PasswordChanged(val value: String) : AuthUiEvent()
    object LoginClicked : AuthUiEvent()
}
