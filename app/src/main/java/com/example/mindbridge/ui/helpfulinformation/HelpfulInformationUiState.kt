package com.example.mindbridge.ui.helpfulinformation

/**
 * UI State for the Helpful Information Screen adhering to MVVM architecture.
 */
data class HelpfulInformationUiState(
    val pleaseCallMe: String = "Margaret",
    val caregiverContact: String = "Emma Wilson · Daughter",
    val phoneNumber: String = "+1 (202) 555-0148",
    val communicationStyle: String = "Speak clearly and give me time to reply.",
    val importantNotes: String = "I enjoy a quiet space and familiar names.",
    val noticeText: String = "Sharing is controlled by you or an authorized caregiver. Review the information before choosing to share it.",
    val selectedTab: Int = 3
)
