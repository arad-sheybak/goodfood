package com.aradsheybak.goodfood.screens.signup.presentation.contract

sealed class SignupViewEffect {
    object NavigateToLogin : SignupViewEffect()
    data class ShowErrorToast(val message: String) : SignupViewEffect()
}