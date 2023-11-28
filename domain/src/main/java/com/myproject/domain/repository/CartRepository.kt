package com.myproject.domain.repository

import com.myproject.domain.models.dish.DishCard

interface CartRepository {
    suspend fun placeAnOrder(dishes: List<DishCard>): Boolean
}
