package com.example.mindbridge.ui.album

data class AlbumPhoto(
    val id: String,
    val title: String,
    val date: String,
    val emojiThumb: String,
    val isSelected: Boolean = false
)

enum class AlbumCategory {
    MY_FAMILY, SPECIAL_DAYS, PLACES_I_LOVE
}

/**
 * UI State for the Family Album Screen adhering to MVVM architecture.
 */
data class AlbumUiState(
    val subtitle: String = "Familiar faces. Favorite moments.",
    val selectedCategory: AlbumCategory = AlbumCategory.MY_FAMILY,
    val photos: List<AlbumPhoto> = listOf(
        AlbumPhoto("1", "Margaret & Lily", "18 May 2006", "👵👧", true),
        AlbumPhoto("2", "Emma", "18 May 2006", "👩"),
        AlbumPhoto("3", "David & Margaret", "23 Aug 2006", "👴👵"),
        AlbumPhoto("4", "Lily & Emma", "14 June 2006", "👧👩")
    ),
    val selectedPersonName: String = "Lily",
    val selectedTab: Int = 1,
    val navigateToVoice: Boolean = false,
    val navigateToPlaces: Boolean = false
)
