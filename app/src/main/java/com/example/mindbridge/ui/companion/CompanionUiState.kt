package com.example.mindbridge.ui.companion

/**
 * UI State for the AI Companion Screen adhering to MVVM architecture.
 */
data class CompanionUiState(
    val userName: String = "Margaret",
    val greeting: String = "Hello, Margaret. How are you feeling today?",
    val subtitle: String = "I'm here to listen, at your pace.",
    val messageInput: String = "",
    val isListening: Boolean = false,
    val chatHistory: List<String> = emptyList()
)
