package com.example.mindbridge.ui.album

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Family Album Screen adhering to MVVM architecture.
 */
class AlbumViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumUiState())
    val uiState: StateFlow<AlbumUiState> = _uiState.asStateFlow()

    fun selectCategory(category: AlbumCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
        if (category == AlbumCategory.PLACES_I_LOVE) {
            _uiState.update { it.copy(navigateToPlaces = true) }
        }
    }

    fun selectPhoto(id: String) {
        _uiState.update { state ->
            val updatedPhotos = state.photos.map { photo ->
                val isSelected = photo.id == id
                photo.copy(isSelected = isSelected)
            }
            val clickedPhoto = updatedPhotos.find { it.id == id }
            val name = clickedPhoto?.title?.split(" & ")?.last() ?: state.selectedPersonName
            state.copy(photos = updatedPhotos, selectedPersonName = name)
        }
    }

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(selectedPersonName = name) }
    }

    fun saveName() {
        // Save action
    }

    fun addPhoto() {
        // Add photo action
    }

    fun openVoice() {
        _uiState.update { it.copy(navigateToVoice = true) }
    }

    fun openPlaces() {
        _uiState.update { it.copy(navigateToPlaces = true) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToVoice = false, navigateToPlaces = false) }
    }

    fun resetState() {
        _uiState.value = AlbumUiState()
    }
}
