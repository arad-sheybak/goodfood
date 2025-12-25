package com.aradsheybak.goodfood.screens.login.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    @param:Json(name = "id")
    val id:Int,
    @param:Json(name="first_name")
    val first_name: String,
    @param:Json(name = "last_name")
    val last_name: String,
    @param:Json(name = "email")
    val email: String,
    @param:Json(name = "img_profile")
    val img_profile: String?,
    @param:Json(name = "token")
    val token: String

)
