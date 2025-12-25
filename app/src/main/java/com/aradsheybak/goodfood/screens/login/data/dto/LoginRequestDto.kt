package com.aradsheybak.goodfood.screens.login.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    @param:Json(name = "username")
    val username: String,
    @param:Json(name = "password")
    val password: String

)
