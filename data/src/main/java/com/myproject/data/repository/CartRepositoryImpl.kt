package com.myproject.data.repository

import com.myproject.data.storage.cart.CartStorage
import com.myproject.data.storage.cart.models.CartItem
import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartStorage: CartStorage
) : CartRepository {
    override suspend fun placeAnOrder(dishes: List<DishCard>): Boolean {
        if(dishes.isEmpty()) return false

        val cartItems = dishes.map { item ->
            CartItem(
                id = item.id,
                name = item.name,
                image = item.image,
                count = item.count,
                priceCurrent = item.price_current,
                priceOld = item.price_old)
        }
        return cartStorage.placeAnOrder(cartItems)
    }
}
