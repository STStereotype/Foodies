package com.myproject.domain.models.dish

data class DishCard(
    val id: Int,
    val name: String,
    val image: String,
    val count: Int,
    val price_current: Int,
    val price_old: Int?
)
