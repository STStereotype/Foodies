package com.myproject.domain.usecase.category

import com.myproject.domain.models.categoty.Category
import com.myproject.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend fun execute(): List<Category> = categoryRepository.getCategories()
}
