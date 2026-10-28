package com.example.mindbridge.ui.profile

/**
<<<<<<< HEAD
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
=======
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
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
)
