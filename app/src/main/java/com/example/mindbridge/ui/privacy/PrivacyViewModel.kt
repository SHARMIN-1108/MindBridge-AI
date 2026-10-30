package com.example.mindbridge.ui.privacy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for Privacy and Sharing Screen adhering to MVVM architecture.
 */
class PrivacyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PrivacyUiState())
    val uiState: StateFlow<PrivacyUiState> = _uiState.asStateFlow()

    fun togglePref(id: String) {
        _uiState.update { state ->
            val updated = state.sharingPrefs.map { pref ->
                if (pref.id == id) {
                    val newEnabled = !pref.isEnabled
                    val newTitle = if (newEnabled) {
                        pref.title.replace(" · Off", " · On")
                    } else {
                        pref.title.replace(" · On", " · Off")
                    }
                    pref.copy(isEnabled = newEnabled, title = newTitle)
                } else pref
            }
            state.copy(sharingPrefs = updated)
        }
    }

    fun reviewCaregiver() {
        // Review caregiver action
    }

    fun reviewPermissions() {
        // Review permissions action
    }

    fun keepConnected() {
        _uiState.update { it.copy(showDisconnectConfirmation = false) }
    }

    fun disconnectCaregiver() {
        _uiState.update { it.copy(showDisconnectConfirmation = false) }
    }

    fun resetState() {
        _uiState.value = PrivacyUiState()
    }
}
