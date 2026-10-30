package com.example.mindbridge.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the My Profile Screen adhering to MVVM architecture.
 */
class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onPreferredNameChanged(name: String) {
        _uiState.update { it.copy(preferredName = name) }
    }

    fun editProfile() {
        _uiState.update { it.copy(navigateToSettings = true) }
    }

    fun manageFamilyConnection() {
        // Manage family connection action
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToSettings = false) }
    }

    fun resetState() {
        _uiState.value = ProfileUiState()
    }
}
