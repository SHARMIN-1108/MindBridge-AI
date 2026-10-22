package com.example.mindbridge.ui.splash

/**
 * UI State for the Splash Screen.
 */
sealed interface SplashUiState {
    object Loading : SplashUiState
    object Completed : SplashUiState
}
