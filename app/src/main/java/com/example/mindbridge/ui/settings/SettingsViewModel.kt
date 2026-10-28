package com.example.mindbridge.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
<<<<<<< HEAD
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the App Settings Screen adhering to MVVM architecture.
=======

/**
 * ViewModel for the Settings Screen adhering to MVVM architecture.
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
 */
class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

<<<<<<< HEAD
    fun openSetting(id: String) {
        // Open setting action
=======
    fun onSettingClick(settingId: String) {
        // Handle setting click
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
    }

    fun logout() {
        // Logout action
    }

    fun stayHere() {
<<<<<<< HEAD
        _uiState.update { it.copy(showLogoutConfirmation = false) }
=======
        // Stay here action
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
    }

    fun resetState() {
        _uiState.value = SettingsUiState()
    }
}
