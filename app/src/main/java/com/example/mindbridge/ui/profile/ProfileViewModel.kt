package com.example.mindbridge.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
<<<<<<< HEAD
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the My Profile Screen adhering to MVVM architecture.
=======

/**
 * ViewModel for the Profile Screen adhering to MVVM architecture.
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
 */
class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

<<<<<<< HEAD
    fun onPreferredNameChanged(name: String) {
        _uiState.update { it.copy(preferredName = name) }
    }

    fun editProfile() {
        _uiState.update { it.copy(navigateToSettings = true) }
=======
    fun editProfile() {
        // Edit profile action
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
    }

    fun manageFamilyConnection() {
        // Manage family connection action
    }

<<<<<<< HEAD
    fun resetNavigation() {
        _uiState.update { it.copy(navigateToSettings = false) }
    }

=======
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
    fun resetState() {
        _uiState.value = ProfileUiState()
    }
}
