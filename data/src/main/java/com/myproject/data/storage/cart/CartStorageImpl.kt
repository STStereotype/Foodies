package com.myproject.data.storage.cart

import com.myproject.data.storage.cart.models.CartItem
import kotlinx.coroutines.delay

class CartStorageImpl : CartStorage {
    override suspend fun placeAnOrder(dishes: List<CartItem>): Boolean {
        delay(500)
        return true
    }
}
