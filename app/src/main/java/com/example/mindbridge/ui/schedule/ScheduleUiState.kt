package com.example.mindbridge.ui.schedule

data class RoutineItem(
    val id: String,
    val time: String,
    val title: String,
    val period: RoutinePeriod,
    val isCompleted: Boolean,
    val subtitle: String? = null
)

enum class RoutinePeriod {
    MORNING, AFTERNOON, EVENING
}

/**
 * UI State for the Daily Schedule Screen adhering to MVVM architecture.
 */
data class ScheduleUiState(
    val dateText: String = "Tuesday, 13 October",
    val subtitleText: String = "One gentle step at a time.",
    val items: List<RoutineItem> = listOf(
        RoutineItem("1", "8:00 AM", "Breakfast", RoutinePeriod.MORNING, true, "Done"),
        RoutineItem("2", "10:00 AM", "Morning Walk", RoutinePeriod.MORNING, false),
        RoutineItem("3", "12:30 PM", "Lunch", RoutinePeriod.AFTERNOON, false),
        RoutineItem("4", "4:00 PM", "Family Call", RoutinePeriod.AFTERNOON, false, "with Emma"),
        RoutineItem("5", "9:00 PM", "Bedtime", RoutinePeriod.EVENING, false)
    ),
    val selectedTab: Int = 0
)
