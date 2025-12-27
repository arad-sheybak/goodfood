package com.aradsheybak.goodfood.screens.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignupResponseDto(
    @param:Json(name = "isSuccessful")
    val isSuccessful: Boolean,
    @param:Json(name = "message")
    val message: String
)
