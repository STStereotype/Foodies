package com.myproject.foodies.screens.foodies.models.viewstate.displaysearchstate

import androidx.navigation.NavController
import com.myproject.domain.models.dish.DishCard
import kotlinx.coroutines.flow.StateFlow

data class DisplaySearchState(
    val dishes: StateFlow<List<DishCard>>,
    val idAndCountDishesInCart: StateFlow<Map<Int, Int>>,
    val informationText: StateFlow<String>,
    val onDish: (navController: NavController, id: Int) -> Unit,
    val onSearchDish: (name: String) -> Unit,
    val onBack: () -> Unit,
    val onIncrease: (id: Int) -> Unit,
    val onDecrease: (id: Int) -> Unit,
)
