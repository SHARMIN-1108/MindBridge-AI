package com.example.mindbridge.ui.notifications

data class NotificationPref(
    val id: String,
    val title: String,
    val description: String,
    val isEnabled: Boolean
)

/**
 * UI State for Notifications Screen adhering to MVVM architecture.
 */
data class NotificationsUiState(
    val preferences: List<NotificationPref> = listOf(
        NotificationPref("1", "Reminders and daily schedule", "Get notified about upcoming routines and reminders.", true),
        NotificationPref("2", "Family updates and shared memories", "Alerts when family members add photos or voice notes.", true),
        NotificationPref("3", "AI Companion check-ins", "Gentle prompts and conversational support reminders.", true)
    ),
    val selectedTab: Int = 3
)
