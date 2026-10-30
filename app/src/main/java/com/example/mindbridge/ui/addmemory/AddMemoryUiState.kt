package com.example.mindbridge.ui.addmemory

/**
 * UI State for the Add a New Memory Screen adhering to MVVM architecture.
 */
data class AddMemoryUiState(
    val title: String = "",
    val titleHint: String = "Title added. You're all set.",
    val date: String = "",
    val description: String = "",
    val associatedPeople: String = "",
    val mediaStatusText: String = "Example media controls – nothing recorded",
    val mediaStatus: String = "Example media controls – nothing recorded",
    val selectedTab: Int = 1,
    val isLoading: Boolean = false,
    val isSavedSuccess: Boolean = false,
    val errorMessage: String? = null
)
