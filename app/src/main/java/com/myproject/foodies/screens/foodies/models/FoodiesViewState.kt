package com.myproject.foodies.screens.foodies.models

sealed class FoodiesViewState {
    object Loading: FoodiesViewState()
    object DisplayFoodies: FoodiesViewState()
}
