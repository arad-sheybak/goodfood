package com.aradsheybak.goodfood.screens.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignupRequestDto(
    @param:Json(name = "first_name")
    val first_name: String,
    @param:Json(name = "last_name")
    val last_name: String,
    @param:Json(name = "email")
    val email: String,
    @param:Json(name = "password")
    val password: String

)
