package com.example.mindbridge.ui.voice

data class VoiceRecord(
    val id: String,
    val title: String,
    val author: String,
    val date: String,
    val duration: String
)

/**
 * UI State for the Voice Memories Screen adhering to MVVM architecture.
 */
data class VoiceUiState(
    val subtitle: String = "Familiar voices, ready to enjoy again.",
    val records: List<VoiceRecord> = listOf(
        VoiceRecord("1", "Family Picnic", "Emma", "18 May 2006", "0:24"),
        VoiceRecord("2", "A Day by the Sea", "David", "23 August 2006", "0:42")
    ),
    val playingId: String? = "1",
    val isPlaying: Boolean = true,
    val playbackProgress: Float = 0.5f,
    val playbackTimeText: String = "Pause - 0:12 / 0:24",
    val showDeleteConfirmationForId: String? = "2",
    val selectedTab: Int = 1
)
