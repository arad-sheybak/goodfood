package com.aradsheybak.goodfood.screens.signup.domain.entity

import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult.Failure

sealed class SignupResult {
    object Success : SignupResult()
    sealed class Failure: SignupResult(){
        object InvalidEmail: Failure()

        object EmailAlreadyExists  : Failure()

        object NetworkError : Failure()

        data class Unknown(
            val throwable: Throwable? = null
        ) : Failure()
    }
}