package com.example.mindbridge.ui.familyupdates

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Family Updates Screen adhering to MVVM architecture.
 */
class FamilyUpdatesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FamilyUpdatesUiState())
    val uiState: StateFlow<FamilyUpdatesUiState> = _uiState.asStateFlow()

    fun openUpdate(id: String) {
        // Open update action
    }

    fun resetState() {
        _uiState.value = FamilyUpdatesUiState()
    }
}
