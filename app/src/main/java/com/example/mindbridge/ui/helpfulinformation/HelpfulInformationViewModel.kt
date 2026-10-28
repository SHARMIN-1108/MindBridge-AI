package com.example.mindbridge.ui.helpfulinformation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Helpful Information Screen adhering to MVVM architecture.
 */
class HelpfulInformationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HelpfulInformationUiState())
    val uiState: StateFlow<HelpfulInformationUiState> = _uiState.asStateFlow()

    fun editInformation() {
        // Edit information action
    }

    fun resetState() {
        _uiState.value = HelpfulInformationUiState()
    }
}
