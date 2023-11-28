package com.myproject.domain.repository

import com.myproject.domain.models.filter.Filter

interface FilterRepository {
    suspend fun getFilters(): List<Filter>
}
