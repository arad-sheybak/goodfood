package com.aradsheybak.goodfood.screens.signup.domain.usecase

import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupCredentials
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupResult
import com.aradsheybak.goodfood.screens.signup.domain.repository.SignupRepository

class SignupUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(
        credentials: SignupCredentials
    ): SignupResult {
        return repository.signup(credentials)
    }
}