package com.example.mindbridge.ui.memory

data class MemoryItem(
    val id: String,
    val date: String,
    val title: String,
    val description: String,
    val duration: String = "0:24"
)

/**
 * UI State for the Memory Journey Screen adhering to MVVM architecture.
 */
data class MemoryUiState(
    val subtitle: String = "The people and moments that make you, you.",
    val memories: List<MemoryItem> = listOf(
        MemoryItem("1", "18 May 2006", "Family Picnic", "Emma, Lily and me in the garden."),
        MemoryItem("2", "14 June 2020", "My Birthday", "Seventy-two candles, so much love."),
        MemoryItem("3", "23 August 2020", "A Day by the Sea", "A sunny afternoon with David.")
    ),
    val selectedTab: Int = 1,
    val navigateToAlbum: Boolean = false,
    val navigateToAddMemory: Boolean = false
)
