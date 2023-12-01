package com.myproject.foodies.screens.foodies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.myproject.foodies.screens.foodies.models.FoodiesEvent
import com.myproject.foodies.screens.foodies.models.FoodiesViewState
import com.myproject.foodies.screens.foodies.views.FoodiesViewDisplay
import com.myproject.foodies.screens.foodies.views.FoodiesViewLoading
import kotlinx.coroutines.delay

@Composable
fun FoodiesScreen(
    navController: NavController,
    viewModel: FoodiesViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    when (val state = viewState.value) {
        FoodiesViewState.Loading -> FoodiesViewLoading()
        FoodiesViewState.DisplayFoodies -> FoodiesViewDisplay(
            navController = navController
        )
    }

    LaunchedEffect(viewState) {
        delay(1000)
        viewModel.send(event = FoodiesEvent.EnterFoodiesDisplay)
    }
}
