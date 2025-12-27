package com.aradsheybak.goodfood.screens.signup.data.mapper

import com.aradsheybak.goodfood.screens.signup.data.dto.SignupResponseDto
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupResult

fun SignupResponseDto.toDomain(): SignupResult{
    return if (isSuccessful) {
        SignupResult.Success
    } else {
        when {
            message.contains("email", ignoreCase = true) ->
                SignupResult.Failure.EmailAlreadyExists

            else ->
                SignupResult.Failure.Unknown()
        }
    }
}