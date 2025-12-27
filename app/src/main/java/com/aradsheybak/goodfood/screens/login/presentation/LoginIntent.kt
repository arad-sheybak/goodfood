package com.aradsheybak.goodfood.screens.login.presentation

sealed class LoginIntent {
    data class UsernameChanged(val username: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    object LoginClicked : LoginIntent()
    object SignupClicked : LoginIntent()
    object ClearError : LoginIntent()

}