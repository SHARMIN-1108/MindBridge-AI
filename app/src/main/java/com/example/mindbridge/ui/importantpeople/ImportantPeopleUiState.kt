package com.example.mindbridge.ui.importantpeople

data class PersonItem(
    val id: String,
    val name: String,
    val relationship: String,
    val notes: String
)

/**
 * UI State for the Important People / People in My Life Screen adhering to MVVM architecture.
 */
data class ImportantPeopleUiState(
    val people: List<PersonItem> = listOf(
        PersonItem("1", "Emma Wilson", "Daughter", "Our afternoon calls"),
        PersonItem("2", "Oliver Wilson", "Grandson", "Loves the garden"),
        PersonItem("3", "Rose Bennett", "Close friend", "Tea on Thursdays"),
        PersonItem("4", "Sarah Miller", "Caregiver", "Helps with routines")
    ),
    val showConfirmation: Boolean = true,
    val confirmationName: String = "Rose",
    val selectedTab: Int = 1
)
