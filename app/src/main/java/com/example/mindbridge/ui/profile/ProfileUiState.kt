package com.example.mindbridge.ui.profile

/**
 * UI State for the Profile Screen adhering to MVVM architecture.
 */
data class ProfileUiState(
    val profileName: String = "Margaret Wilson",
    val preferredName: String = "Margaret",
    val textAndLanguage: String = "Large text · English",
    val soundAndVoice: String = "Voice prompts on · Gentle volume",
    val familyConnectionTitle: String = "Family connection",
    val familyConnectionDetail: String = "Emma Wilson · Daughter",
    val familyConnectionStatus: String = "Connected · Example status",
    val selectedTab: Int = 3
)
