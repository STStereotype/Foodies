package com.myproject.foodies.navigation.tabs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.myproject.foodies.navigation.NavGraphTabs
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.screens.cart.CartScreen
import com.myproject.foodies.screens.cart.CartViewModel
import com.myproject.foodies.screens.details.DetailsScreen
import com.myproject.foodies.screens.details.DetailsViewModel
import com.myproject.foodies.screens.foodies.FoodiesScreen
import com.myproject.foodies.screens.foodies.FoodiesViewModel

fun NavGraphBuilder.foodiesGraph(
    navController: NavController
) {
    navigation(
        route = NavGraphTabs.Foodies.route,
        startDestination = FoodiesGraphDestinations.Foodies.destination
    ) {
        composable(FoodiesGraphDestinations.Foodies.destination) {
            val viewModel: FoodiesViewModel = hiltViewModel()
            FoodiesScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(FoodiesGraphDestinations.Details.destination) {
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(FoodiesGraphDestinations.Cart.destination) {
            val viewModel: CartViewModel = hiltViewModel()
            CartScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
