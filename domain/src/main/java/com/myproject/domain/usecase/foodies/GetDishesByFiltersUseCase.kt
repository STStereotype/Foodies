package com.myproject.domain.usecase.foodies

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.filter.Filter
import com.myproject.domain.repository.FoodiesRepository

class GetDishesByFiltersUseCase(
    private val foodiesRepository: FoodiesRepository
) {
    suspend fun execute(filters: List<Filter>): List<DishCard> =
        foodiesRepository.getDishesByFilters(filters)

    suspend fun execute(dish: List<DishCard>, filters: List<Filter>): List<DishCard> =
        foodiesRepository.getDishesByFilters(dish, filters)
}
