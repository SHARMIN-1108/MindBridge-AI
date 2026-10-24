package com.example.mindbridge.ui.addmemory

/**
 * UI State for the Add a New Memory Screen adhering to MVVM architecture.
 */
data class AddMemoryUiState(
    val title: String = "A morning in the garden",
    val titleHint: String = "Title added. You're all set.",
    val date: String = "13 October 2026",
    val description: String = "Emma and I planted flowers after breakfast.",
    val associatedPeople: String = "Margaret, Emma",
    val mediaStatus: String = "Example media controls · nothing recorded",
    val selectedTab: Int = 1
)
