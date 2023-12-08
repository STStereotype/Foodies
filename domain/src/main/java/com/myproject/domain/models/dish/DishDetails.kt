package com.myproject.domain.models.dish

data class DishDetails(
    val dish: DishCard,
    val description: String,
    val energy_per_100_grams: Double,
    val proteins_per_100_grams: Double,
    val fats_per_100_grams: Double,
    val carbohydrates_per_100_grams: Double,
)
