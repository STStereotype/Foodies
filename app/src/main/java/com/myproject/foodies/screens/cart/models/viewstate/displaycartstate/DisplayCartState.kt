package com.myproject.foodies.screens.cart.models.viewstate.displaycartstate

import androidx.navigation.NavController
import com.myproject.domain.models.cart.Cart
import kotlinx.coroutines.flow.StateFlow

data class DisplayCartState(
    val cart: StateFlow<List<Cart>>,
    val costDish: StateFlow<Int>,
    val onIncrease: (id: Int) -> Unit,
    val onDecrease: (id: Int) -> Unit,
    val placeAnOrder: () -> Unit,
    val onBack: (navController: NavController) -> Unit
)
