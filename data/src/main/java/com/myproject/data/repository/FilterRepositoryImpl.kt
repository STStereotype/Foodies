package com.myproject.data.repository

import com.myproject.data.storage.filter.FilterStorage
import com.myproject.domain.models.filter.Filter
import com.myproject.domain.repository.FilterRepository

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage
) : FilterRepository {
    override suspend fun getFilters(): List<Filter> {
        val items = filterStorage.getFilters()

        if (items.isEmpty()) return listOf()

        return items.map { filter ->
            Filter(
                id = filter.id,
                name = filter.name
            )
        }
    }
}