package com.example.mindbridge.ui.aiconversations

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the AI Conversation History Screen adhering to MVVM architecture.
 */
class AiConversationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AiConversationsUiState())
    val uiState: StateFlow<AiConversationsUiState> = _uiState.asStateFlow()

    fun startNewConversation() {
        // Start new conversation action
    }

    fun openConversation(id: String) {
        // Open conversation action
    }

    fun removeConversation(id: String) {
        _uiState.update { state ->
            state.copy(conversations = state.conversations.filter { it.id != id })
        }
    }

    fun resetState() {
        _uiState.value = AiConversationsUiState()
    }
}
