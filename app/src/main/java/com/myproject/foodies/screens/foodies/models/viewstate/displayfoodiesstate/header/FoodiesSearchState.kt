package com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header

import androidx.navigation.NavController

data class FoodiesSearchState(
    val onSearch: (navController: NavController) -> Unit
)
