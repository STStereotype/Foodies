package com.myproject.foodies.screens.foodies.models.viewstate

import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.DisplayFoodiesState
import com.myproject.foodies.screens.foodies.models.viewstate.displaysearchstate.DisplaySearchState

sealed class FoodiesViewState {
    object Loading : FoodiesViewState()
    data class FoodiesDisplay(
        val displayFoodiesState: DisplayFoodiesState,
    ) : FoodiesViewState()
    data class SearchDisplay(
        val displaySearchState: DisplaySearchState,
    ) : FoodiesViewState()
}
