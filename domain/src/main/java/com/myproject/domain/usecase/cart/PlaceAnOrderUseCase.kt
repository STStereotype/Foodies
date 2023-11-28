package com.myproject.domain.usecase.cart

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.CartRepository

class PlaceAnOrderUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute(dishes: List<DishCard>): Boolean = cartRepository.placeAnOrder(dishes)
}
