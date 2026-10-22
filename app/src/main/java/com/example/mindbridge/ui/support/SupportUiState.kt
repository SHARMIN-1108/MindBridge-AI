package com.example.mindbridge.ui.support

data class EmergencyContact(
    val name: String = "Emma Wilson - Daughter",
    val phone: String = "+1 (202) 555-0148",
    val note: String = "Not a live calling service."
)

/**
 * UI State for the Emergency Support Screen adhering to MVVM architecture.
 */
data class SupportUiState(
    val headerTitle: String = "A helping hand, when you need one.",
    val subtitle: String = "Choose the kind of help you need. You don't have to manage everything alone.",
    val emergencyContact: EmergencyContact = EmergencyContact(),
    val noticeTitle: String = "Before real-world use",
    val noticeText: String = "Emergency calling must be configured and tested before real-world use. These are static example controls.",
    val selectedTab: Int = 0
)
