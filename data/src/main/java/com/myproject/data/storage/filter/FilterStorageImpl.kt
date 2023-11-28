package com.myproject.data.storage.filter

import com.google.gson.reflect.TypeToken
import com.myproject.data.common.FetchJson
import com.myproject.data.storage.filter.models.FilterItem

class FilterStorageImpl : FilterStorage {
    override suspend fun getFilters(): List<FilterItem> = FetchJson<FilterItem>(
        object : TypeToken<List<FilterItem>>() {}.type
    ).fetch(JSONFilter)
}

private val JSONFilter = "[ { \"id\": 1, \"name\": \"Новинка\" }, { \"id\": 2, \"name\": \"Вегетарианское блюдо\" }, { \"id\": 3, \"name\": \"Хит!\" }, { \"id\": 4, \"name\": \"Острое\" }, { \"id\": 5, \"name\": \"Со скидкой\" } ]"
