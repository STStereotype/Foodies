package com.myproject.data.storage.cart.models

data class CartItem(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val image: String,
    val measure: Int? = null,
    val measureUnit: String? = null,
    var count: Int,
    val priceCurrent: Int,
    val priceOld: Int?,
    val tagIds: List<Int>
)
