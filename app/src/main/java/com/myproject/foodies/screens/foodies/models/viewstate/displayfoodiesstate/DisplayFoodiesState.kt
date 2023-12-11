package com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate

import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header.FoodiesHeaderState

data class DisplayFoodiesState(
    val foodiesState: FoodiesState,
    val foodiesHeaderState: FoodiesHeaderState
)
