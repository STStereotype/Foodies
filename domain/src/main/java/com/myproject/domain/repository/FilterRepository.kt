package com.myproject.domain.repository

import com.myproject.domain.models.filter.Filter


interface FilterRepository {

    fun getFilters(): List<Filter>
}