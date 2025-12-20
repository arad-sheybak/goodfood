package com.aradsheybak.goodfood.screens.login.domain.usecase

import com.aradsheybak.goodfood.screens.login.domain.repository.LoginRepository

class LoginUseCase(val repository: LoginRepository) {
    suspend operator fun invoke() {
        repository.login()
    }
}