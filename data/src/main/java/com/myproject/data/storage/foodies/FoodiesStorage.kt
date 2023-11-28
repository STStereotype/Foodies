package com.myproject.data.storage.foodies

import com.myproject.data.storage.foodies.models.FoodiesItem

interface FoodiesStorage {
    suspend fun getDishes(): List<FoodiesItem>?
}
