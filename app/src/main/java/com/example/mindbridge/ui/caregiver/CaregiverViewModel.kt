package com.example.mindbridge.ui.caregiver

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Caregiver Overview Screen adhering to MVVM architecture.
 */
class CaregiverViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CaregiverUiState())
    val uiState: StateFlow<CaregiverUiState> = _uiState.asStateFlow()

    fun openSupport() {
        _uiState.update { it.copy(navigateToSupport = true) }
    }

    fun openMemories() {
        _uiState.update { it.copy(navigateToMemories = true) }
    }

    fun openReminders() {
        _uiState.update { it.copy(navigateToReminders = true) }
    }

    fun openGames() {
        _uiState.update { it.copy(navigateToGames = true) }
    }

    fun openSchedule() {
        _uiState.update { it.copy(navigateToSchedule = true) }
    }

    fun resetNavigation() {
        _uiState.update {
            it.copy(
                navigateToSupport = false,
                navigateToMemories = false,
                navigateToReminders = false,
                navigateToGames = false,
                navigateToSchedule = false
            )
        }
    }

    fun resetState() {
        _uiState.value = CaregiverUiState()
    }
}
