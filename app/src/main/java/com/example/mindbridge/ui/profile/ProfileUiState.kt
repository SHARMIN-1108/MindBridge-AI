package com.example.mindbridge.ui.profile

/**
 * UI State for the Profile Screen adhering to MVVM architecture.
 */
data class ProfileUiState(
    val fullName: String = "Margaret Wilson",
    val profileName: String = "Margaret Wilson",
    val preferredName: String = "Margaret",
    val email: String = "margaret.wilson@email.com",
    val textPreference: String = "Large text • English",
    val soundPreference: String = "Voice prompts on • Gentle volume",
    val textAndLanguage: String = "Large text • English",
    val soundAndVoice: String = "Voice prompts on • Gentle volume",
    val familyConnectionTitle: String = "Family connection",
    val familyConnectionDetail: String = "Emma Wilson • Daughter",
    val familyConnectionStatus: String = "Connected • Example status",
    val familyConnectionText: String = "Emma Wilson • Daughter\nConnected • Example status",
    val profilePicture: String? = null,
    val selectedTab: Int = 3,
    val navigateToSettings: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
