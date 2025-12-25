package com.aradsheybak.goodfood.screens.signup.domain.repository

import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupCredentials
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupResult

interface SignupRepository{
    suspend fun signup(credentials: SignupCredentials): SignupResult
}