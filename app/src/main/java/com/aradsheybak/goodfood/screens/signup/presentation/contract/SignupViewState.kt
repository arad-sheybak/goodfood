package com.aradsheybak.goodfood.screens.signup.presentation.contract

data class SignupViewState(
    val firstName: String = "",
    val lastName: String = "",
    val email:String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)