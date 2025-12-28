package com.aradsheybak.goodfood.screens.signup.presentation.contract

sealed class SignupIntent {
    data class FirstNameChanged(val firstName: String) : SignupIntent()
    data class LastNameChanges(val lastName: String) : SignupIntent()
    data class EmailChanged(val email: String) : SignupIntent()
    data class PasswordChanged(val password: String): SignupIntent()
    data class ConfirmPasswordChanged(val confirmPassword: String): SignupIntent()

    object SignupClicked: SignupIntent()
    object ClearError: SignupIntent()

}