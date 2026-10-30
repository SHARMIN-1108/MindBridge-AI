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

    fun onPeopleChanged(people: String) {
        _uiState.update { it.copy(associatedPeople = people) }
    }

    fun onAssociatedPeopleChanged(people: String) {
        _uiState.update { it.copy(associatedPeople = people) }
    }

    fun addPhoto() {
        _uiState.update { it.copy(mediaStatusText = "Photo added successfully") }
    }

    fun recordVoiceNote() {
        _uiState.update { it.copy(mediaStatusText = "Voice note recorded (0:15)") }
    }

    fun saveMemory() {
        _uiState.update { it.copy(mediaStatusText = "Memory saved successfully") }
    }

    fun resetState() {
        _uiState.value = AddMemoryUiState()
    }
}
