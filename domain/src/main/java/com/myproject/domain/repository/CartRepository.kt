package com.myproject.domain.repository

import com.myproject.domain.models.cart.Cart

interface CartRepository {
    suspend fun getCartDishes(): List<Cart>
    suspend fun addCartDish(cart: Cart)
    suspend fun removeCartDish(id: Int)
    suspend fun placeAnOrder(dishes: List<Cart>): Boolean
}
