package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishDetails
import com.myproject.domain.repository.FoodiesRepository

class GetDishesByIdUseCase(
    private val foodiesRepository: FoodiesRepository
) {
    suspend fun execute(id: Int): DishDetails? = foodiesRepository.getDishesById(id)
}
