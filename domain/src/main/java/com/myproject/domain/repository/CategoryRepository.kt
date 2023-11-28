package com.myproject.domain.repository

import com.myproject.domain.models.categoty.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
