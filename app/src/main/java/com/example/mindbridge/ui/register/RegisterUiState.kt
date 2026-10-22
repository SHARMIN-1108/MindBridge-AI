package com.example.mindbridge.ui.register

/**
 * UI State for the Register / Create Account Screen adhering to MVVM architecture.
 */
data class RegisterUiState(
    val fullName: String = "Margaret Wilson",
    val email: String = "margaret.wilson@email.com",
    val password: String = "password123",
    val confirmPassword: String = "password123",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val role: UserRole = UserRole.PERSON_USING_APP,
    val agreedToTerms: Boolean = true,
    val isLoading: Boolean = false,
    val isRegisterSuccess: Boolean = false,
    val navigateToLogin: Boolean = false
)

enum class UserRole {
    PERSON_USING_APP,
    FAMILY_CAREGIVER
}
