package com.myproject.domain.models.cart

import com.myproject.domain.models.dish.DishCard

data class Cart(
    val dish: DishCard,
    val count: Int
)
