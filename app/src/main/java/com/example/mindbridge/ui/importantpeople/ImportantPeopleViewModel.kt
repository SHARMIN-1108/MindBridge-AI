package com.example.mindbridge.ui.importantpeople

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Important People Screen adhering to MVVM architecture.
 */
class ImportantPeopleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ImportantPeopleUiState())
    val uiState: StateFlow<ImportantPeopleUiState> = _uiState.asStateFlow()

    fun addPerson() {
        // Add person action
    }

    fun editPerson(id: String) {
        // Edit person action
    }

    fun removePerson(id: String) {
        _uiState.update { state ->
            state.copy(people = state.people.filter { it.id != id }, showConfirmation = false)
        }
    }

    fun dismissConfirmation() {
        _uiState.update { it.copy(showConfirmation = false) }
    }

    fun resetState() {
        _uiState.value = ImportantPeopleUiState()
    }
}
