package com.myproject.foodies.screens.foodies.models.state.displayFoodiesState

import com.myproject.foodies.screens.foodies.models.state.displayFoodiesState.header.FoodiesHeaderState

data class DisplayFoodiesState(
    val foodiesState: FoodiesState,
    val foodiesHeaderState: FoodiesHeaderState
)
