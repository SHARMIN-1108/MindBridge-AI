package com.example.mindbridge.ui.addmemory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Add a New Memory Screen adhering to MVVM architecture.
 */
class AddMemoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddMemoryUiState())
    val uiState: StateFlow<AddMemoryUiState> = _uiState.asStateFlow()

    fun onTitleChanged(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onDateChanged(date: String) {
        _uiState.update { it.copy(date = date) }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onAssociatedPeopleChanged(people: String) {
        _uiState.update { it.copy(associatedPeople = people) }
    }

    fun addPhoto() {
        // Add photo action
    }

    fun recordVoiceNote() {
        // Record voice note action
    }

    fun saveMemory() {
        // Save memory action
    }

    fun resetState() {
        _uiState.value = AddMemoryUiState()
    }
}
