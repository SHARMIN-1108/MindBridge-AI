package com.example.mindbridge.ui.welcome

/**
 * UI State for the Welcome Screen.
 */
sealed interface WelcomeUiState {
    data object Idle : WelcomeUiState
    data object NavigateToGetStarted : WelcomeUiState
    data object NavigateToLogin : WelcomeUiState
}
