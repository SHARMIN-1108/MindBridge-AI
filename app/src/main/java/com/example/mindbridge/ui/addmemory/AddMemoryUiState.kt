package com.example.mindbridge.ui.addmemory

/**
 * UI State for the Add a New Memory Screen adhering to MVVM architecture.
 */
data class AddMemoryUiState(
    val title: String = "A morning in the garden",
<<<<<<< HEAD
    val date: String = "13 October 2026",
    val description: String = "Emma and I planted flowers after breakfast.",
    val associatedPeople: String = "Margaret, Emma",
    val mediaStatusText: String = "Example media controls · nothing recorded",
=======
    val titleHint: String = "Title added. You're all set.",
    val date: String = "13 October 2026",
    val description: String = "Emma and I planted flowers after breakfast.",
    val associatedPeople: String = "Margaret, Emma",
    val mediaStatus: String = "Example media controls · nothing recorded",
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
    val selectedTab: Int = 1
)
