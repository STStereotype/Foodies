package com.myproject.domain.usecase.cart

import com.myproject.domain.repository.CartRepository
import com.myproject.domain.usecase.BaseUseCase

class PlaceAnOrderUseCase(
    private val cartRepository: CartRepository
) : BaseUseCase<Boolean> {
    override fun execute(): Boolean = cartRepository.placeAnOrder()
}