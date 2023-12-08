package com.myproject.foodies.screens.foodies.models

import com.myproject.foodies.screens.foodies.models.state.displayFoodiesState.DisplayFoodiesState
import com.myproject.foodies.screens.foodies.models.state.displaySearchState.DisplaySearchState

sealed class FoodiesViewState {
    object Loading : FoodiesViewState()
    data class FoodiesDisplay(
        val displayFoodiesState: DisplayFoodiesState,
    ) : FoodiesViewState()
    data class SearchDisplay(
        val displaySearchState: DisplaySearchState,
    ) : FoodiesViewState()
}
