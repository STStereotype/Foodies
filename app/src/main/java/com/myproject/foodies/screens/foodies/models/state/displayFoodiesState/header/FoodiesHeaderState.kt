package com.myproject.foodies.screens.foodies.models.state.displayFoodiesState.header

data class FoodiesHeaderState(
    val foodiesCategoryState: FoodiesCategoryState,
    val foodiesFilterState: FoodiesFilterState,
    val foodiesSearchState: FoodiesSearchState
)
