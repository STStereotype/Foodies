package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.FoodiesRepository

class GetMealFreeDishesUseCase(
    private val foodiesRepository: FoodiesRepository
) {
    suspend fun execute(): List<DishCard>? = foodiesRepository.getMealFreeDishes()
}
