package com.example.mindbridge.ui.caregiver

/**
 * UI State for the Caregiver Overview Screen adhering to MVVM architecture.
 */
data class CaregiverUiState(
    val memberName: String = "Margaret Wilson",
    val memberRole: String = "Family member - Change",
    val footerText: String = "Example summary of app activity only. Review Margaret's sharing choices together.",
    val selectedTab: Int = 3,
    val navigateToSupport: Boolean = false
)
