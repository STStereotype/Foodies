package com.myproject.foodies.screens.cart.models

sealed class CartEvent {
    object EnterCartDisplay : CartEvent()
}