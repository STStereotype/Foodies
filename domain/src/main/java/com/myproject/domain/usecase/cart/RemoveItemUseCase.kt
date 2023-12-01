package com.myproject.domain.usecase.cart

import com.myproject.domain.repository.CartRepository

class RemoveItemUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute(id: Int) = cartRepository.removeCartDish(id)
}
