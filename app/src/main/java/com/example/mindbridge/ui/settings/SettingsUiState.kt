package com.example.mindbridge.ui.settings

data class SettingItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconEmoji: String = "",
    val icon: String = ""
)

/**
 * UI State for the App Settings Screen adhering to MVVM architecture.
 */
data class SettingsUiState(
    val settings: List<SettingItem> = listOf(
        SettingItem("1", "Text Size", "Large", "T", "T"),
        SettingItem("2", "Sound and Voice", "Voice on • Gentle volume", "🔊", "🔊"),
        SettingItem("3", "Notifications", "Reminders and family updates on", "🔔", "🔔"),
        SettingItem("4", "Privacy", "Review example sharing choices", "🛡️", "🛡️"),
        SettingItem("5", "Language", "English", "🌐", "🌐"),
        SettingItem("6", "Help", "Simple guides and support", "❓", "❓")
    ),
    val settingsList: List<SettingItem> = emptyList(),
    val showLogoutConfirmation: Boolean = true,
    val selectedTab: Int = 3,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val navigateToNotifications: Boolean = false,
    val navigateToPrivacy: Boolean = false,
    val navigateToAbout: Boolean = false
)
