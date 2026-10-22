package com.example.mindbridge.ui.companion

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the AI Companion Screen adhering to MVVM architecture.
 */
class CompanionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CompanionUiState())
    val uiState: StateFlow<CompanionUiState> = _uiState.asStateFlow()

    fun onMessageChanged(message: String) {
        _uiState.update { it.copy(messageInput = message) }
    }

    fun sendMessage() {
        val currentMsg = _uiState.value.messageInput.trim()
        if (currentMsg.isNotEmpty()) {
            _uiState.update { state ->
                state.copy(
                    chatHistory = state.chatHistory + currentMsg,
                    messageInput = ""
                )
            }
        }
    }

    fun toggleListening() {
        _uiState.update { it.copy(isListening = !it.isListening) }
    }

    fun sendPrompt(prompt: String) {
        _uiState.update { state ->
            state.copy(chatHistory = state.chatHistory + prompt)
        }
    }

    fun resetState() {
        _uiState.value = CompanionUiState()
    }
}
