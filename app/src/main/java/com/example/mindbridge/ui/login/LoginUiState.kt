package com.example.mindbridge.ui.login

/**
 * UI State for the Login Screen adhering to MVVM architecture.
 */
data class LoginUiState(
    val email: String = "margaret.wilson@email.com",
    val password: String = "password123",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccess: Boolean = false,
    val navigateToRegister: Boolean = false
)
