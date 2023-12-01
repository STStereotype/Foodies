package com.myproject.foodies.screens.foodies.views

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
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.ui.theme.FoodiesTheme

@Composable
fun FoodiesViewDisplay(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            navController.navigate(
                route = FoodiesGraphDestinations.Details.destination,
                navOptions = navOptions {
                    popUpTo(navController.graph.findStartDestination().id)
                })
        }) {
            Text(text = "<- Details", style = FoodiesTheme.typography.bigTitle)
        }
        Text(text = "Foodies", style = FoodiesTheme.typography.bigTitle)
        TextButton(onClick = {
            navController.navigate(
                route = FoodiesGraphDestinations.Cart.destination,
                navOptions = navOptions {
                    popUpTo(navController.graph.findStartDestination().id)
                })
        }) {
            Text(text = "Cart ->", style = FoodiesTheme.typography.bigTitle)
        }
    }
}
