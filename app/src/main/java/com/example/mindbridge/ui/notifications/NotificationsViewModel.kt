package com.example.mindbridge.ui.notifications

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for Notifications Screen adhering to MVVM architecture.
 */
class NotificationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUiState())
    val uiState: StateFlow<NotificationsUiState> = _uiState.asStateFlow()

    fun togglePreference(id: String) {
        _uiState.update { state ->
            val updated = state.preferences.map { pref ->
                if (pref.id == id) pref.copy(isEnabled = !pref.isEnabled) else pref
            }
            state.copy(preferences = updated)
        }
    }

    fun resetState() {
        _uiState.value = NotificationsUiState()
    }
}
