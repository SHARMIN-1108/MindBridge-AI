package com.example.mindbridge.ui.privacy

data class SharingPref(
    val id: String,
    val title: String,
    val subtitle: String,
    val isEnabled: Boolean
)

/**
 * UI State for Privacy and Sharing Screen adhering to MVVM architecture.
 */
data class PrivacyUiState(
    val headerTitle: String = "Your choices, clearly explained",
    val subtitle: String = "These are example preferences, not confirmed live sharing. Review choices before making changes.",
    val caregiverName: String = "Emma Wilson · Daughter · Example",
    val sharingPrefs: List<SharingPref> = listOf(
        SharingPref("1", "Shared memories · On", "Emma: selected memories", true),
        SharingPref("2", "Voice recordings · Off", "Emma: not shared", false),
        SharingPref("3", "Activity summaries · On", "Emma: weekly activity counts", true)
    ),
    val showDisconnectConfirmation: Boolean = true,
    val selectedTab: Int = 3
)
