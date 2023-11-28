package com.myproject.data.storage.category

import com.google.gson.reflect.TypeToken
import com.myproject.data.common.FetchJson
import com.myproject.data.storage.category.models.CategoryItem

class CategoryStorageImpl : CategoryStorage {
    override suspend fun getCategories(): List<CategoryItem> = FetchJson<CategoryItem>(
        object : TypeToken<List<CategoryItem>>() {}.type
    ).fetch(JSONCategory)
}

private val JSONCategory = "[ { \"id\": 676153, \"name\": \"Горячие блюда\" }, { \"id\": 676154, \"name\": \"Суши\" }, { \"id\": 676155, \"name\": \"Соусы\" }, { \"id\": 676156, \"name\": \"Детское меню\" }, { \"id\": 676157, \"name\": \"Подарочные сертификаты\" }, { \"id\": 676159, \"name\": \"Напитки\" }, { \"id\": 676160, \"name\": \"Горячие закуски\" }, { \"id\": 676161, \"name\": \"Готовим дома\" }, { \"id\": 676162, \"name\": \"Средства индивидуальной защиты\" }, { \"id\": 676163, \"name\": \"Салаты\" }, { \"id\": 676164, \"name\": \"Супы\" }, { \"id\": 676165, \"name\": \"Десерты\" }, { \"id\": 676166, \"name\": \"Вок\" }, { \"id\": 676167, \"name\": \"Бургеры\" }, { \"id\": 676168, \"name\": \"Роллы\" }, { \"id\": 676169, \"name\": \"Наборы\" }, { \"id\": 676170, \"name\": \"Сашими\" }, { \"id\": 676171, \"name\": \"Половинки роллов\" }, { \"id\": 676172, \"name\": \"Сувениры\" }, { \"id\": 676173, \"name\": \"Бизнес ланчи\" }, { \"id\": 1512275, \"name\": \"Фестиваль гёдза\" }, { \"id\": 1667058, \"name\": \"Мероприятия\" } ]"
