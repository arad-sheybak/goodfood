package com.aradsheybak.goodfood.screens.login.domain.entity

data class AuthenticatedUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val profileImageUrl: String?,
    val authToken: String
)
