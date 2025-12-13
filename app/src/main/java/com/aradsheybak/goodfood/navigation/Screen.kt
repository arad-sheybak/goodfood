package com.aradsheybak.goodfood.navigation

sealed class Screen(val route: String) {
    object splash : Screen("splash")
    object onboarding : Screen("onboarding")

    object login : Screen("login")
}