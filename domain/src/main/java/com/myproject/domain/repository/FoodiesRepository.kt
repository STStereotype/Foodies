package com.myproject.domain.repository

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.dish.DishDetails
import com.myproject.domain.models.filter.Filter

interface FoodiesRepository {
    suspend fun getDishes(): List<DishCard>

    suspend fun getDishesByFilters(filters: List<Filter>): List<DishCard>

    suspend fun getDishesByFilters(dishes: List<DishCard>, filters: List<Filter>): List<DishCard>

    suspend fun getDishesById(id: Int): DishDetails

    suspend fun getDishesByCategoryId(id: Int): List<DishCard>

    suspend fun getDishesByName(name: String): List<DishCard>
}
