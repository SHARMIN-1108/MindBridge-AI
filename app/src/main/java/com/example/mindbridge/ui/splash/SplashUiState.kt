package com.example.mindbridge.ui.splash

/**
 * UI State for the Splash Screen.
 */
sealed interface SplashUiState {
    data object Loading : SplashUiState
    data object Completed : SplashUiState
}
