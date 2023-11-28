package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.FoodiesRepository

class GetDishesByCategoryIdUseCase(
    private val foodiesRepository: FoodiesRepository
){
    suspend fun execute(id: Int): List<DishCard>? = foodiesRepository.getDishesByCategoryId(id)
}
