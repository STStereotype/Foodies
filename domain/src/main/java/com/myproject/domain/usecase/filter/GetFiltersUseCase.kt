package com.myproject.domain.usecase.filter

import com.myproject.domain.models.filter.Filter
import com.myproject.domain.repository.FilterRepository
import com.myproject.domain.usecase.BaseUseCase

class GetFiltersUseCase(
    private val filterRepository: FilterRepository
) : BaseUseCase<List<Filter>> {
    override fun execute(): List<Filter> = filterRepository.getFilters()
}