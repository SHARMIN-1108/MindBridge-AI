package com.example.mindbridge.ui.voice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Voice Memories Screen adhering to MVVM architecture.
 */
class VoiceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VoiceUiState())
    val uiState: StateFlow<VoiceUiState> = _uiState.asStateFlow()

    fun togglePlay(id: String) {
        _uiState.update { state ->
            if (state.playingId == id) {
                state.copy(isPlaying = !state.isPlaying)
            } else {
                state.copy(playingId = id, isPlaying = true)
            }
        }
    }

    fun requestDelete(id: String) {
        _uiState.update { it.copy(showDeleteConfirmationForId = id) }
    }

    fun confirmDelete() {
        _uiState.update { state ->
            val targetId = state.showDeleteConfirmationForId
            val updatedRecords = state.records.filter { it.id != targetId }
            state.copy(records = updatedRecords, showDeleteConfirmationForId = null)
        }
    }

    fun cancelDelete() {
        _uiState.update { it.copy(showDeleteConfirmationForId = null) }
    }

    fun recordMemory() {
        // Record action
    }

    fun resetState() {
        _uiState.value = VoiceUiState()
    }
}
