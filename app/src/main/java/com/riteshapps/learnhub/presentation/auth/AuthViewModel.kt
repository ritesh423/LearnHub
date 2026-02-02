package com.riteshapps.learnhub.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riteshapps.learnhub.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(AuthUiState())
        private set

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.UsernameChanged -> {
                state = state.copy(username = event.value)
            }

            is AuthUiEvent.PasswordChanged -> {
                state = state.copy(password = event.value)
            }

            AuthUiEvent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            try {
                loginUseCase(
                    state.username,
                    state.password
                )
                state = state.copy(
                    isLoading = false,
                    isLoggedIn = true
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message ?: "Login failed"
                )
            }
        }
    }
}
