package com.example.mindbridge.ui.preferences

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Personal Preferences Screen adhering to MVVM architecture.
 */
class PreferencesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

    fun editPreference(id: String) {
        // Edit preference action
    }

    fun savePreferences() {
        // Save preferences action
    }

    fun resetState() {
        _uiState.value = PreferencesUiState()
    }
}
