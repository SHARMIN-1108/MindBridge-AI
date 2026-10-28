package com.example.mindbridge.ui.profile

/**
 * UI State for the My Profile Screen adhering to MVVM architecture.
 */
data class ProfileUiState(
    val fullName: String = "Margaret Wilson",
    val preferredName: String = "Margaret",
    val textPreference: String = "Large text · English",
    val soundPreference: String = "Voice prompts on · Gentle volume",
    val familyConnectionText: String = "Emma Wilson · Daughter\nConnected · Example status",
    val selectedTab: Int = 3,
    val navigateToSettings: Boolean = false
)
