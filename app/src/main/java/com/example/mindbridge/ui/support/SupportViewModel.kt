package com.example.mindbridge.ui.support

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Emergency Support Screen adhering to MVVM architecture.
 */
class SupportViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SupportUiState())
    val uiState: StateFlow<SupportUiState> = _uiState.asStateFlow()

    fun callCaregiver() {
        // Call caregiver action
    }

    fun callEmergencyServices() {
        // Call emergency action
    }

    fun viewContacts() {
        // View contacts action
    }

    fun resetState() {
        _uiState.value = SupportUiState()
    }
}
