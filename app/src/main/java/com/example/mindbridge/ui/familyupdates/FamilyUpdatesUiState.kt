package com.example.mindbridge.ui.familyupdates

sealed class FeedItem {
    data class PhotoUpdate(
        val id: String,
        val title: String,
        val senderAndDate: String,
        val message: String
    ) : FeedItem()

    data class AudioUpdate(
        val id: String,
        val title: String,
        val details: String,
        val icon: String
    ) : FeedItem()

    data class CallUpdate(
        val id: String,
        val title: String,
        val details: String,
        val icon: String
    ) : FeedItem()
}

/**
 * UI State for the Family Updates Screen adhering to MVVM architecture.
 */
data class FamilyUpdatesUiState(
    val subtitle: String = "A little news from the people you love.",
    val feedHeader: String = "Approved updates · Example feed",
    val feedItems: List<FeedItem> = listOf(
        FeedItem.PhotoUpdate("1", "A photo from our picnic", "Emma · 12 October 2026", "\"A lovely moment with you and Lily in the garden.\""),
        FeedItem.AudioUpdate("2", "A saved voice memory", "David · 11 Oct · Our seaside afternoon", "🎤"),
        FeedItem.CallUpdate("3", "Our next family call", "Emma · 13 Oct · Today at 4:00 PM", "📞")
    ),
    val showEmptyState: Boolean = true,
    val selectedTab: Int = 1
)
