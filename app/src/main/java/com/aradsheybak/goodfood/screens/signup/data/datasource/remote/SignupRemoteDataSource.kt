package com.aradsheybak.goodfood.screens.signup.data.datasource.remote

import com.aradsheybak.goodfood.screens.signup.data.dto.SignupRequestDto
import com.aradsheybak.goodfood.screens.signup.data.dto.SignupResponseDto

class SignupRemoteDataSource(val api: SignupApi) {
    suspend fun signup(request: SignupRequestDto): SignupResponseDto {
        return api.userSignup(request)
    }
}