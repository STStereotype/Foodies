package com.myproject.domain.repository

import com.myproject.domain.models.categoty.Category

interface CategoryRepository {

    fun getCategories(): List<Category>
}