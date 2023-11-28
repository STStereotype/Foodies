package com.myproject.data.repository

import com.myproject.data.storage.category.CategoryStorage
import com.myproject.domain.models.categoty.Category
import com.myproject.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryStorage: CategoryStorage
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        val items = categoryStorage.getCategories()

        if (items.isEmpty()) return listOf()

        return items.map { category ->
            Category(
                id = category.id,
                name = category.name
            )
        }
    }
}
