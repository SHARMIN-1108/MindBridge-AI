package com.example.mindbridge.ui.settings

data class SettingItem(
    val id: String,
    val title: String,
    val subtitle: String,
<<<<<<< HEAD
    val iconEmoji: String
)

/**
 * UI State for the App Settings Screen adhering to MVVM architecture.
 */
data class SettingsUiState(
    val settings: List<SettingItem> = listOf(
=======
    val icon: String
)

/**
 * UI State for the Settings Screen adhering to MVVM architecture.
 */
data class SettingsUiState(
    val settingsList: List<SettingItem> = listOf(
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
        SettingItem("1", "Text Size", "Large", "T"),
        SettingItem("2", "Sound and Voice", "Voice on · Gentle volume", "🔊"),
        SettingItem("3", "Notifications", "Reminders and family updates on", "🔔"),
        SettingItem("4", "Privacy", "Review example sharing choices", "🔒"),
        SettingItem("5", "Language", "English", "🌐"),
        SettingItem("6", "Help", "Simple guides and support", "❓")
    ),
<<<<<<< HEAD
    val showLogoutConfirmation: Boolean = true,
=======
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
    val selectedTab: Int = 3
)
