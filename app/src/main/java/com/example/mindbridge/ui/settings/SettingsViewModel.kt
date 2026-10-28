package com.example.mindbridge.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the App Settings Screen adhering to MVVM architecture.
 */
class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun openSetting(id: String) {
        // Open setting action
    }

    fun logout() {
        // Logout action
    }

    fun stayHere() {
        _uiState.update { it.copy(showLogoutConfirmation = false) }
    }

    fun resetState() {
        _uiState.value = SettingsUiState()
    }
}
