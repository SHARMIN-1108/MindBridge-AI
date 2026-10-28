package com.example.mindbridge.ui.dailycheckin

data class MoodOption(
    val id: String,
    val title: String,
    val icon: String
)

/**
 * UI State for the Daily Check-In Screen adhering to MVVM architecture.
 */
data class DailyCheckInUiState(
    val moods: List<MoodOption> = listOf(
        MoodOption("1", "Happy", "😊"),
        MoodOption("2", "Calm", "☀️"),
        MoodOption("3", "Tired", "🌙"),
        MoodOption("4", "Worried", "☁️"),
        MoodOption("5", "Not Sure", "❓")
    ),
    val selectedMoodId: String = "2", // Calm selected by default
    val noteText: String = "I enjoyed my tea in the garden.",
    val selectedTab: Int = 0
)
