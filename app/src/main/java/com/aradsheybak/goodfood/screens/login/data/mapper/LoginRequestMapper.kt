package com.aradsheybak.goodfood.screens.login.data.mapper

import com.aradsheybak.goodfood.screens.login.data.dto.LoginRequestDto
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginCredentials

fun LoginCredentials.toRequestDto(): LoginRequestDto{
    return LoginRequestDto(
        username = this.username,
        password = this.password
    )
}