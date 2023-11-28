package com.myproject.data.storage.cart

import com.myproject.data.storage.cart.models.CartItem

interface CartStorage {
    suspend fun placeAnOrder(dishes: List<CartItem>): Boolean
}

