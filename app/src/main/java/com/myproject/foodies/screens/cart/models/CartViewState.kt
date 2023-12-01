package com.myproject.foodies.screens.cart.models

sealed class CartViewState {
    object Loading: CartViewState()
    object DisplayCart: CartViewState()
}