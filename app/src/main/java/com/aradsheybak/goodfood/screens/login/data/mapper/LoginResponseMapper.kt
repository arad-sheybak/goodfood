package com.aradsheybak.goodfood.screens.login.data.mapper

import com.aradsheybak.goodfood.screens.login.data.dto.LoginResponseDto
import com.aradsheybak.goodfood.screens.login.domain.entity.AuthenticatedUser
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult

fun LoginResponseDto.toDomain(): AuthenticatedUser {

    return AuthenticatedUser(
        id = this.id,
        firstName = this.first_name,
        lastName = this.last_name,
        email = this.email,
        profileImageUrl = this.img_profile,
        authToken = this.token
    )
}