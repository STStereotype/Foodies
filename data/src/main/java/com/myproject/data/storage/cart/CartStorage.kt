package com.myproject.data.storage.cart

import com.myproject.data.storage.cart.models.CartItem

interface CartStorage {
    val cart: List<CartItem>

    suspend fun addCartDish(dish: CartItem)
    suspend fun removeCartDish(id: Int)
    suspend fun placeAnOrder(dishes: List<CartItem>): Boolean
}

