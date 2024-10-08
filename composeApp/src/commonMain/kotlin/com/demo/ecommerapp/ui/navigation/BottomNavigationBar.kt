package com.demo.ecommerapp.ui.navigation

import ecommerapp.composeapp.resources.*
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomNavigationRoute(
    val route: String,
    val label: String,
    val icon: DrawableResource,
) {
    data object Home : BottomNavigationRoute("/home", "Home", icon = Res.drawable.home)
    data object Categories : BottomNavigationRoute("/categories", "Categories", icon = Res.drawable.categories)
    data object Cart : BottomNavigationRoute("/cart", "Cart", icon = Res.drawable.cart)
    data object Profile : BottomNavigationRoute("/profile", "Profile", icon = Res.drawable.profile)
}