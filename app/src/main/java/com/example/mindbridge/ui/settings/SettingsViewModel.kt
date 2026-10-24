package com.example.mindbridge.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Settings Screen adhering to MVVM architecture.
 */
class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onSettingClick(settingId: String) {
        // Handle setting click
    }

    fun logout() {
        // Logout action
    }

    fun stayHere() {
        // Stay here action
    }

    fun resetState() {
        _uiState.value = SettingsUiState()
    }
}
