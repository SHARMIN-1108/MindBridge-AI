package com.example.mindbridge.ui.reminders

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Reminder Center Screen adhering to MVVM architecture.
 */
class RemindersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RemindersUiState())
    val uiState: StateFlow<RemindersUiState> = _uiState.asStateFlow()

    fun editReminder(id: String) {
        // Edit reminder action
    }

    fun addReminder() {
        // Add reminder action
    }

    fun resetState() {
        _uiState.value = RemindersUiState()
    }
}
