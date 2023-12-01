package com.myproject.domain.usecase.cart

import com.myproject.domain.models.cart.Cart
import com.myproject.domain.repository.CartRepository

class GetCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute(): List<Cart> = cartRepository.getCartDishes()
}
