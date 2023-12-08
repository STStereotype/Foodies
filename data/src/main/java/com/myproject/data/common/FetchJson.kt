package com.myproject.data.common

import com.google.gson.Gson
import java.lang.reflect.Type

class FetchJson<T>(
    val typeToken: Type
) {
    fun fetch(jsonAsString: String): List<T> {
        return Gson().fromJson(jsonAsString, typeToken)
    }
}
