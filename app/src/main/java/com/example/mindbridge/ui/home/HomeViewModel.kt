package com.example.mindbridge.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Home Dashboard Screen adhering to MVVM architecture.
 */
class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun toggleReminder(id: String) {
        _uiState.update { state ->
            val updatedReminders = state.reminders.map { reminder ->
                if (reminder.id == id) {
                    reminder.copy(isCompleted = !reminder.isCompleted)
                } else {
                    reminder
                }
            }
            val completed = updatedReminders.count { it.isCompleted }
            state.copy(reminders = updatedReminders, completedCount = completed)
        }
    }

    fun selectTab(tab: HomeTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun openSchedule() {
        _uiState.update { it.copy(navigateToSchedule = true) }
    }

    fun openCompanion() {
        _uiState.update { it.copy(navigateToCompanion = true) }
    }

    fun openGames() {
        _uiState.update { it.copy(navigateToGames = true) }
    }

    fun openMemory() {
        _uiState.update { it.copy(navigateToMemory = true) }
    }

    fun openReminders() {
        _uiState.update { it.copy(navigateToReminders = true) }
    }

    fun resetNavigation() {
        _uiState.update {
            it.copy(
                navigateToSchedule = false,
                navigateToCompanion = false,
                navigateToGames = false,
                navigateToMemory = false,
                navigateToReminders = false
            )
        }
    }
}
