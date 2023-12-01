package com.myproject.foodies.navigation

sealed class NavGraphTabs(val route: String) {
    object Main: NavGraphTabs("mainNav")
    object Foodies: NavGraphTabs("foodiesNav")
}
