package com.aradsheybak.goodfood.screens.login.domain.repository

import com.aradsheybak.goodfood.screens.login.domain.entity.LoginCredentials
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult

interface LoginRepository{
        suspend fun login(credentials: LoginCredentials): LoginResult
    }