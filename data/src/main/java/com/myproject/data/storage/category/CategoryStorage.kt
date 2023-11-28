package com.myproject.data.storage.category

import com.myproject.data.storage.category.models.CategoryItem

interface CategoryStorage {
    suspend fun getCategories(): List<CategoryItem>
}
