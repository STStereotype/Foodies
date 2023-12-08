package com.myproject.foodies.screens.details.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.myproject.domain.models.dish.DishDetails
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.screens.details.DetailsViewModel
import com.myproject.foodies.screens.details.models.DetailsViewState
import com.myproject.foodies.ui.theme.FoodiesTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailsViewDisplay(
    state: DetailsViewState.DisplayDetails,
    navController: NavController,
    onAddDishToCart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Details ${state.dish.value!!.dish.name}", style = FoodiesTheme.typography.bigTitle)
        TextButton(onClick = {
            navController.navigate(
                route = FoodiesGraphDestinations.Foodies.destination,
                navOptions = navOptions {
                    onAddDishToCart()
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                })
        }) {
            Text(text = "Foodies ->", style = FoodiesTheme.typography.bigTitle)
        }
    }
}