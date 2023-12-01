package com.myproject.foodies.navigation.destination

sealed class MainGraphDestinations(val destination: String) {
    object Splash: MainGraphDestinations("mainNav_splashScreen")
    object Main: MainGraphDestinations("mainNav_mainScreen")
}