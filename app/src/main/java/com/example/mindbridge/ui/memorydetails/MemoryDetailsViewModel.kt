package com.example.mindbridge.ui.memorydetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Memory Details Screen adhering to MVVM architecture.
 */
class MemoryDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MemoryDetailsUiState())
    val uiState: StateFlow<MemoryDetailsUiState> = _uiState.asStateFlow()

    fun playVoiceNote() {
        // Play voice note action
    }

    fun editMemory() {
        // Edit memory action
    }

    fun resetState() {
        _uiState.value = MemoryDetailsUiState()
    }
}
