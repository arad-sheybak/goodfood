package com.aradsheybak.goodfood.screens.login.data.datasource.remote

import com.aradsheybak.goodfood.screens.login.data.dto.LoginRequestDto
import com.aradsheybak.goodfood.screens.login.data.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/users/auth-login")
    suspend fun userLogin(
        @Body loginRequest: LoginRequestDto
    ): LoginResponseDto


}