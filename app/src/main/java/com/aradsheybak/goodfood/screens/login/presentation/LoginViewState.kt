package com.aradsheybak.goodfood.screens.login.presentation

data class LoginViewState(
    val username: String ="",
    val password: String="",
    val isLoading: Boolean = false,
    val error: String? = null
)
