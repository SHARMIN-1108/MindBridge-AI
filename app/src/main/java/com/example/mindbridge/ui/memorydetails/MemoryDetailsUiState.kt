package com.example.mindbridge.ui.memorydetails

/**
 * UI State for the Memory Details Screen adhering to MVVM architecture.
 */
data class MemoryDetailsUiState(
    val title: String = "Family Picnic",
    val date: String = "18 May 2026",
    val subtitle: String = "Emma, Lily and me in the garden.",
    val description: String = "We shared tea and sandwiches in the sunshine. Lily helped choose flowers for the table.",
    val associatedPeople: String = "With Margaret, Emma and Lily",
    val voiceNoteTitle: String = "Emma's voice note",
    val voiceNoteSubtitle: String = "0:24 · Playback example",
    val selectedTab: Int = 1
)
