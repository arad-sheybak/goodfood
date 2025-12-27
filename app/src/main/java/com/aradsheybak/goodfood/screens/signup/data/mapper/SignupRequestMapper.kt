package com.aradsheybak.goodfood.screens.signup.data.mapper

import com.aradsheybak.goodfood.screens.signup.data.dto.SignupRequestDto
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupCredentials

fun SignupCredentials.toRequestDto(): SignupRequestDto{
    return SignupRequestDto(
        first_name = this.firstName,
        last_name = this.lastName,
        email = this.email,
        password = this.password
    )

}
