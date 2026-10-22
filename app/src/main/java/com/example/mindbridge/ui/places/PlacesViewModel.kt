package com.example.mindbridge.ui.places

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Safe / Familiar Places Screen adhering to MVVM architecture.
 */
class PlacesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PlacesUiState())
    val uiState: StateFlow<PlacesUiState> = _uiState.asStateFlow()

    fun getDirections(placeId: String) {
        // Directions action
    }

    fun callCaregiver() {
        // Call caregiver action
    }

    fun resetState() {
        _uiState.value = PlacesUiState()
    }
}
