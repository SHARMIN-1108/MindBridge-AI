package com.example.mindbridge.ui.games

data class GameItem(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailEmoji: String
)

enum class GameDifficulty {
    EASY, MEDIUM
}

/**
 * UI State for the Brain / Cognitive Games Screen adhering to MVVM architecture.
 */
data class GamesUiState(
    val difficulty: GameDifficulty = GameDifficulty.EASY,
    val games: List<GameItem> = listOf(
        GameItem("1", "Match the Pictures", "Find two pictures that belong together.", "🌻"),
        GameItem("2", "Remember the Objects", "Look at familiar things, then recall them.", "🍎"),
        GameItem("3", "Complete the Pattern", "Choose the shape that comes next.", "🟦")
    ),
    val selectedTab: Int = 2,
    val navigateToProgress: Boolean = false
)
