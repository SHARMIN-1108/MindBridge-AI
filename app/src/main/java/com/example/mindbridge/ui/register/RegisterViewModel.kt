package com.example.mindbridge.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Register Screen adhering to MVVM architecture.
 */
class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onFullNameChanged(name: String) {
        _uiState.update { it.copy(fullName = name) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChanged(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.update { it.copy(confirmPasswordVisible = !it.confirmPasswordVisible) }
    }

    fun onRoleSelected(role: UserRole) {
        _uiState.update { it.copy(role = role) }
    }

    fun onTermsToggled(agreed: Boolean) {
        _uiState.update { it.copy(agreedToTerms = agreed) }
    }

    fun register() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000L)
            _uiState.update { it.copy(isLoading = false, isRegisterSuccess = true) }
        }
    }

    fun navigateToLogin() {
        _uiState.update { it.copy(navigateToLogin = true) }
    }

    fun resetState() {
        _uiState.value = RegisterUiState()
    }
}
