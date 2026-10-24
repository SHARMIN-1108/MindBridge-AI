package com.example.mindbridge.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Profile Screen adhering to MVVM architecture.
 */
class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun editProfile() {
        // Edit profile action
    }

    fun manageFamilyConnection() {
        // Manage family connection action
    }

    fun resetState() {
        _uiState.value = ProfileUiState()
    }
}
