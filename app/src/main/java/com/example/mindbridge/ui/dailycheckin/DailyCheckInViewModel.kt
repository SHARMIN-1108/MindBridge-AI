package com.example.mindbridge.ui.dailycheckin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Daily Check-In Screen adhering to MVVM architecture.
 */
class DailyCheckInViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DailyCheckInUiState())
    val uiState: StateFlow<DailyCheckInUiState> = _uiState.asStateFlow()

    fun selectMood(id: String) {
        _uiState.update { it.copy(selectedMoodId = id) }
    }

    fun onNoteChanged(note: String) {
        _uiState.update { it.copy(noteText = note) }
    }

    fun saveCheckIn() {
        // Save check-in action
    }

    fun skipForNow() {
        // Skip action
    }

    fun resetState() {
        _uiState.value = DailyCheckInUiState()
    }
}
