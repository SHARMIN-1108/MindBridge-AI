package com.example.mindbridge.ui.about

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for About MindBridge AI Screen adhering to MVVM architecture.
 */
class AboutViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AboutUiState())
    val uiState: StateFlow<AboutUiState> = _uiState.asStateFlow()

    fun openLink(id: String) {
        // Open link action
    }

    fun resetState() {
        _uiState.value = AboutUiState()
    }
}
