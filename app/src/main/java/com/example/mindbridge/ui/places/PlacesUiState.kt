package com.example.mindbridge.ui.places

data class PlaceItem(
    val id: String,
    val title: String,
    val description: String,
    val iconEmoji: String
)

/**
 * UI State for the Safe / Familiar Places Screen adhering to MVVM architecture.
 */
data class PlacesUiState(
    val subtitle: String = "Places you know, with a little help to get there.",
    val places: List<PlaceItem> = listOf(
        PlaceItem("1", "Home", "Your home on Willow Lane", "🏠"),
        PlaceItem("2", "Family Home", "Emma's home - Oak Street", "🏡"),
        PlaceItem("3", "Local Park", "Riverside Park - garden entrance", "🌳")
    ),
    val selectedTab: Int = 0
)
