package com.example.mindbridge.ui.memory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Memory Journey Screen adhering to MVVM architecture.
 */
class MemoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MemoryUiState())
    val uiState: StateFlow<MemoryUiState> = _uiState.asStateFlow()

    fun addMemory() {
        // Handle adding memory action
    }

    fun openAlbum() {
        _uiState.update { it.copy(navigateToAlbum = true) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToAlbum = false) }
    }

    fun resetState() {
        _uiState.value = MemoryUiState()
    }
}
