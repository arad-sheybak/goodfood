package com.aradsheybak.goodfood.screens.login.domain.usecase

import com.aradsheybak.goodfood.screens.login.domain.entity.LoginCredentials
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult
import com.aradsheybak.goodfood.screens.login.domain.repository.LoginRepository

class LoginUseCase(val repository: LoginRepository) {

    suspend operator fun invoke(credentials: LoginCredentials): LoginResult {

        return repository.login(credentials = credentials)
    }
}