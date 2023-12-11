package com.myproject.foodies.screens.cart.models.viewstate

import com.myproject.foodies.screens.cart.models.viewstate.displaycartstate.DisplayCartState

sealed class CartViewState {
    object Loading: CartViewState()
    data class DisplayCart(
        val displayCartState: DisplayCartState
    ): CartViewState()
}
