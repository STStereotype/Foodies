package com.myproject.foodies.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.myproject.foodies.navigation.NavGraphTabs
import com.myproject.foodies.navigation.destination.MainGraphDestinations
import com.myproject.foodies.screens.MainScreen
import com.myproject.foodies.screens.splash.SplashScreen

fun NavGraphBuilder.mainGraph(
    navController: NavController
) {
    navigation(
        route = NavGraphTabs.Main.route,
        startDestination = MainGraphDestinations.Splash.destination
    ) {
        composable(MainGraphDestinations.Splash.destination) {
            SplashScreen(
                navController = navController,
                navigateDestination = MainGraphDestinations.Main.destination
            )
        }
        composable(MainGraphDestinations.Main.destination) {
            MainScreen()
        }
    }
}
