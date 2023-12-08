package com.myproject.domain.usecase.cart

import com.myproject.domain.models.cart.Cart
import com.myproject.domain.repository.CartRepository

class PlaceAnOrderUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute(cart: List<Cart>): Boolean = cartRepository.placeAnOrder(cart)
}
