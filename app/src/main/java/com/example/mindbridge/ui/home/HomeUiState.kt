package com.example.mindbridge.ui.home

data class Reminder(
    val id: String,
    val title: String,
    val time: String,
    val isCompleted: Boolean
)

/**
 * UI State for the Home Dashboard Screen adhering to MVVM architecture.
 */
data class HomeUiState(
    val userName: String = "Margaret",
    val dateText: String = "Tuesday, 13 October 2026",
    val greeting: String = "Good morning",
    val reminders: List<Reminder> = listOf(
        Reminder("1", "Breakfast", "8:00 AM", true),
        Reminder("2", "Morning Walk", "10:00 AM", false),
        Reminder("3", "Call with Emma", "4:00 PM", false)
    ),
    val completedCount: Int = 1,
    val totalCount: Int = 5,
    val selectedTab: HomeTab = HomeTab.HOME,
    val navigateToSchedule: Boolean = false,
    val navigateToCompanion: Boolean = false,
    val navigateToGames: Boolean = false,
    val navigateToMemory: Boolean = false,
    val navigateToReminders: Boolean = false
)

enum class HomeTab {
    HOME, MEMORIES, ACTIVITIES, PROFILE
}
