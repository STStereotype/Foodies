package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.FoodiesRepository

class GetDishesByNameUseCase(
    private val foodiesRepository: FoodiesRepository
) {
    suspend fun execute(name: String): List<DishCard>? = foodiesRepository.getDishesByName(name)
}
