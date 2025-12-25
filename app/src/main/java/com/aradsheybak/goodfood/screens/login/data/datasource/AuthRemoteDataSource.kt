package com.aradsheybak.goodfood.screens.login.data.datasource

import com.aradsheybak.goodfood.screens.login.data.datasource.remote.AuthApi
import com.aradsheybak.goodfood.screens.login.data.dto.LoginRequestDto
import com.aradsheybak.goodfood.screens.login.data.dto.LoginResponseDto

class AuthRemoteDataSource(private val api: AuthApi) {
    suspend fun login(request: LoginRequestDto): LoginResponseDto {
        return api.userLogin(loginRequest = request)
    }
}