package com.myproject.foodies.navigation.tabs

import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.myproject.foodies.di.ViewModelFactoryProvider
import com.myproject.foodies.navigation.NavGraphTabs
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.screens.cart.CartScreen
import com.myproject.foodies.screens.details.DetailsScreen
import com.myproject.foodies.screens.details.DetailsViewModel
import com.myproject.foodies.screens.foodies.FoodiesScreen
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

fun NavGraphBuilder.foodiesGraph(
    navController: NavController
) {
    navigation(
        route = NavGraphTabs.Foodies.route,
        startDestination = FoodiesGraphDestinations.Foodies.destination
    ) {
        composable(FoodiesGraphDestinations.Foodies.destination) {
            FoodiesScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable(
            route = FoodiesGraphDestinations.Details.destination,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val factory = EntryPointAccessors.fromActivity(
                LocalContext.current as Activity,
                ViewModelFactoryProvider::class.java
            ).detailsViewModelFactory()

            val id = backStackEntry.arguments?.getInt("id")
            DetailsScreen(
                navController = navController,
                viewModel = viewModel(
                    factory = DetailsViewModel.provideDetailsViewModelFactory(factory, id)),
            )
        }
        composable(FoodiesGraphDestinations.Cart.destination) {
            CartScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
    }
}
