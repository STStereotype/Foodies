package com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.navigation.NavController
import com.myproject.domain.models.dish.DishCard
import kotlinx.coroutines.flow.StateFlow

data class FoodiesState(
    val dishes: StateFlow<List<DishCard>>,
    val idAndCountDishesInCart: StateFlow<Map<Int, Int>>,
    val costDish: StateFlow<Int>,
    val informationText: StateFlow<String>,
    val lazyGridState: StateFlow<LazyGridState>,
    val onDish: (navController: NavController, id: Int) -> Unit,
    val onIncrease: (id: Int) -> Unit,
    val onDecrease: (id: Int) -> Unit,
    val onCart: (navController: NavController) -> Unit
)
