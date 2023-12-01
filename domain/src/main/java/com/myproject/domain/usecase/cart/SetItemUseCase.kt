package com.myproject.domain.usecase.cart

import com.myproject.domain.models.cart.Cart
import com.myproject.domain.repository.CartRepository

class SetItemUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute(cart: Cart) = cartRepository.addCartDish(cart)
}
