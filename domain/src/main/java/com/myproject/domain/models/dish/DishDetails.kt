package com.myproject.domain.models.dish

data class DishDetails(
    val id: Int,
    val name: String,
    val description: String,
    val measure: Int,
    val measure_unit: String,
    val energy_per_100_grams: Double,
    val proteins_per_100_grams: Double,
    val fats_per_100_grams: Double,
    val carbohydrates_per_100_grams: Double,
)
