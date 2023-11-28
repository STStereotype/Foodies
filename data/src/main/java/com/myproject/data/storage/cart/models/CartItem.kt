package com.myproject.data.storage.cart.models

data class CartItem(
    val id: Int,
    val name: String,
    val image: String,
    val count: Int,
    val priceCurrent: Int,
    val priceOld: Int?
)
