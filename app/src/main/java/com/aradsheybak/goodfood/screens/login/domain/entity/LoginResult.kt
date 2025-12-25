package com.aradsheybak.goodfood.screens.login.domain.entity

sealed class LoginResult {
    data class Success(
        val user: AuthenticatedUser
    ) : LoginResult()

    sealed class Failure : LoginResult() {

        object InvalidCredentials : Failure()

        object NetworkError : Failure()

        data class Unknown(
            val throwable: Throwable? = null
        ) : Failure()
    }
}