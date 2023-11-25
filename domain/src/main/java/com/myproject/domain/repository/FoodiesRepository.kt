package com.myproject.domain.repository

import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.dish.DishDetails

interface FoodiesRepository {

    fun getSharpDishes(): List<DishCard>

    fun getMealFreeDishes(): List<DishCard>

    fun getDiscountedDishes(): List<DishCard>

    fun getDishesById(): List<DishDetails>

    fun getDishesByCategory(): List<DishCard>

    fun getDishesByName(): List<DishCard>
}
