package com.myproject.domain.usecase.category

import com.myproject.domain.models.categoty.Category
import com.myproject.domain.repository.CategoryRepository
import com.myproject.domain.usecase.BaseUseCase

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
): BaseUseCase<List<Category>> {
    override fun execute(): List<Category> = categoryRepository.getCategories()
}
