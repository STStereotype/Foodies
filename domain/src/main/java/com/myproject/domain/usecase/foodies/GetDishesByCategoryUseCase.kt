package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.FoodiesRepository
import com.myproject.domain.usecase.BaseUseCase

class GetDishesByCategoryUseCase(
    private val foodiesRepository: FoodiesRepository
) : BaseUseCase<List<DishCard>> {
    override fun execute(): List<DishCard> = foodiesRepository.getDishesByCategory()
}