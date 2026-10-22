package com.example.mindbridge.ui.schedule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Daily Schedule Screen adhering to MVVM architecture.
 */
class ScheduleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    fun toggleItem(id: String) {
        _uiState.update { state ->
            val updatedItems = state.items.map { item ->
                if (item.id == id) {
                    val newCompleted = !item.isCompleted
                    item.copy(
                        isCompleted = newCompleted,
                        subtitle = if (item.period == RoutinePeriod.MORNING && item.id == "1") {
                            if (newCompleted) "Done" else null
                        } else item.subtitle
                    )
                } else {
                    item
                }
            }
            state.copy(items = updatedItems)
        }
    }

    fun addReminder() {
        // Handle adding reminder
    }

    fun resetState() {
        _uiState.value = ScheduleUiState()
    }
}
