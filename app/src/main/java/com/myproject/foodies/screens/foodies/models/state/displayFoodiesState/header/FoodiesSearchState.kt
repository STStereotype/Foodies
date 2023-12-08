package com.myproject.foodies.screens.foodies.models.state.displayFoodiesState.header

import androidx.navigation.NavController

data class FoodiesSearchState(
    val onSearch: (navController: NavController) -> Unit
)
