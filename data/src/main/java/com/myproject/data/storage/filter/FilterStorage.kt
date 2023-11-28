package com.myproject.data.storage.filter

import com.myproject.data.storage.filter.models.FilterItem

interface FilterStorage {
    suspend fun getFilters() : List<FilterItem>
}
