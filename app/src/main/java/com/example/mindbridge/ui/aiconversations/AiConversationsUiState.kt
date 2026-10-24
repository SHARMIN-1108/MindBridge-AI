package com.example.mindbridge.ui.aiconversations

data class ConversationItem(
    val id: String,
    val title: String,
    val dateTime: String,
    val snippet: String
)

/**
 * UI State for the AI Conversation History Screen adhering to MVVM architecture.
 */
data class AiConversationsUiState(
    val conversations: List<ConversationItem> = listOf(
        ConversationItem("1", "A lovely garden morning", "13 October · 9:20 AM", "You told me about planting flowers with Emma."),
        ConversationItem("2", "Songs we enjoy", "12 October · 3:10 PM", "We talked about familiar songs and favorite music."),
        ConversationItem("3", "Remembering the seaside", "11 October · 2:00 PM", "A sunny afternoon with David by the sea.")
    ),
    val selectedTab: Int = 0
)
