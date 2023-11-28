package com.myproject.domain.repository

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.dish.DishDetails

interface FoodiesRepository {
    suspend fun getNewDishes(): List<DishCard>?

    suspend fun getVegetarianDishes(): List<DishCard>?

    suspend fun getHitsDishes(): List<DishCard>?

    suspend fun getSharpDishes(): List<DishCard>?

    suspend fun getExpressMenu(): List<DishCard>?

    suspend fun getDishesById(id: Int): DishDetails?

    suspend fun getDishesByCategoryId(id: Int): List<DishCard>?

    suspend fun getDishesByName(name: String): List<DishCard>?
}
