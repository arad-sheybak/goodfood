package com.aradsheybak.goodfood.screens.signup.data.datasource.remote

import com.aradsheybak.goodfood.screens.signup.data.dto.SignupRequestDto
import com.aradsheybak.goodfood.screens.signup.data.dto.SignupResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApi {
    @POST("api/v1/users/auth-register")
    suspend fun userSignup(@Body signupRequest: SignupRequestDto): SignupResponseDto
}