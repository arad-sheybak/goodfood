package com.aradsheybak.goodfood.screens.login.presentation

sealed class LoginViewEffect {
    object NavigateToHome : LoginViewEffect()
    object NavigateToSignup : LoginViewEffect()
    data class ShowErrorToast(val message: String) : LoginViewEffect()
}