package com.example.mindbridge.ui.welcome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Welcome Screen adhering to MVVM architecture.
 */
class WelcomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<WelcomeUiState>(WelcomeUiState.Idle)
    val uiState: StateFlow<WelcomeUiState> = _uiState.asStateFlow()

    fun onGetStartedClicked() {
        _uiState.value = WelcomeUiState.NavigateToGetStarted
    }

    fun onLoginClicked() {
        _uiState.value = WelcomeUiState.NavigateToLogin
    }

    fun resetState() {
        _uiState.value = WelcomeUiState.Idle
    }
}
