package com.example.mindbridge.ui.games

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Brain / Cognitive Games Screen adhering to MVVM architecture.
 */
class GamesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GamesUiState())
    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    fun setDifficulty(difficulty: GameDifficulty) {
        _uiState.update { it.copy(difficulty = difficulty) }
    }

    fun playGame(gameId: String) {
        // Handle game play action
    }

    fun openProgress() {
        _uiState.update { it.copy(navigateToProgress = true) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToProgress = false) }
    }

    fun resetState() {
        _uiState.value = GamesUiState()
    }
}
