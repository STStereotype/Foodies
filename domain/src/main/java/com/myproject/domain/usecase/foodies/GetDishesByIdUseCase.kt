package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.dish.DishDetails
import com.myproject.domain.repository.FoodiesRepository
import com.myproject.domain.usecase.BaseUseCase

class GetDishesByIdUseCase(
    private val foodiesRepository: FoodiesRepository
) : BaseUseCase<List<DishDetails>> {
    override fun execute(): List<DishDetails> = foodiesRepository.getDishesById()
}