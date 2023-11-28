package com.myproject.domain.usecase.filter

import com.myproject.domain.models.filter.Filter
import com.myproject.domain.repository.FilterRepository

class GetFiltersUseCase(
    private val filterRepository: FilterRepository
) {
    suspend fun execute(): List<Filter> = filterRepository.getFilters()
}
