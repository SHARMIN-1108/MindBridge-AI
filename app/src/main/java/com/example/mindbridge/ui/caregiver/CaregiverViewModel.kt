package com.example.mindbridge.ui.caregiver

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Caregiver Overview Screen adhering to MVVM architecture.
 */
class CaregiverViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CaregiverUiState())
    val uiState: StateFlow<CaregiverUiState> = _uiState.asStateFlow()

    fun viewMemories() {
        // View memories action
    }

    fun manageReminders() {
        // Manage reminders action
    }

    fun contactFamilyMember() {
        _uiState.update { it.copy(navigateToSupport = true) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToSupport = false) }
    }

    fun resetState() {
        _uiState.value = CaregiverUiState()
    }
}
