package com.aradsheybak.goodfood.navigation.bottomNavigation

import com.aradsheybak.goodfood.R

sealed class BottomNavigationItems(val route: String, val title: String, val icon: Int){

    object Home : BottomNavigationItems(route = "home", "Home", icon = R.drawable.ic_username)
    object Basket : BottomNavigationItems(route = "basket", "Basket", icon = R.drawable.ic_username)
    object Favorites : BottomNavigationItems(route = "favorites", "Favorites", icon = R.drawable.ic_username)
    object Profile : BottomNavigationItems(route = "profile", "Profile", icon = R.drawable.ic_username)


}