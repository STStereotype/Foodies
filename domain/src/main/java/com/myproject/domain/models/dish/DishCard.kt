package com.myproject.domain.models.dish

data class DishCard(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val measure: Int? = null,
    val measure_unit: String? = null,
    val image: String,
    val price_current: Int,
    val price_old: Int?,
    val tag_ids: List<Int>
)
