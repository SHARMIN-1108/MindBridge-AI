package com.example.mindbridge.ui.preferences

data class PreferenceItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: String
)

/**
 * UI State for the Personal Preferences / Things I Like Screen adhering to MVVM architecture.
 */
data class PreferencesUiState(
    val preferences: List<PreferenceItem> = listOf(
        PreferenceItem("1", "Favorite activities", "Walking · Gentle brain games", "💡"),
        PreferenceItem("2", "Music", "Familiar songs · Jazz", "🎵"),
        PreferenceItem("3", "Foods", "Tea · Apple pie", "🍽️"),
        PreferenceItem("4", "Hobbies", "Gardening · Reading", "🪴"),
        PreferenceItem("5", "Familiar places", "Home · Riverside Park", "📍"),
        PreferenceItem("6", "Conversation topics", "Family · Garden · Seaside", "💬")
    ),
    val selectedTab: Int = 3
)
