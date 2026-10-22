package com.example.mindbridge.ui.reminders

data class ReminderItem(
    val id: String,
    val title: String,
    val timeOrDate: String,
    val status: String,
    val isCompleted: Boolean,
    val isToday: Boolean
)

/**
 * UI State for the Reminder Center Screen adhering to MVVM architecture.
 */
data class RemindersUiState(
    val dateText: String = "Tuesday, 13 October",
    val subtitleText: String = "A little reminder for everyday moments.",
    val reminders: List<ReminderItem> = listOf(
        ReminderItem("1", "Breakfast", "8:00 AM · Completed", "Completed", true, true),
        ReminderItem("2", "Morning Walk", "10:00 AM - To do", "To do", false, true),
        ReminderItem("3", "Lunch", "12:30 PM - To do", "To do", false, true),
        ReminderItem("4", "Call with Emma", "4:00 PM - To do", "To do", false, true),
        ReminderItem("5", "Community appointment", "Wed, 14 Oct - 11:00 AM - To do", "To do", false, false),
        ReminderItem("6", "Water the garden", "Thu, 15 Oct - 9:30 AM - To do", "To do", false, false)
    ),
    val selectedTab: Int = 0
)
