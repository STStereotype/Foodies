package com.myproject.data.repository

import com.myproject.data.storage.foodies.FoodiesStorage
import com.myproject.data.storage.foodies.models.FoodiesItem
import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.dish.DishDetails
import com.myproject.domain.models.filter.Filter
import com.myproject.domain.repository.FoodiesRepository

class FoodiesRepositoryImpl(
    private val foodiesStorage: FoodiesStorage
) : FoodiesRepository {
    override suspend fun getDishes(): List<DishCard> = foodiesStorage.getDishes().map {
        convertFoodiesItemToDishCard(it)
    }

    override suspend fun getDishesByFilters(filters: List<Filter>) =
        getDishesByFilters(
            dishes = foodiesStorage.getDishes().map { convertFoodiesItemToDishCard(it) },
            filters = filters)

    override suspend fun getDishesByFilters(
        dishes: List<DishCard>,
        filters: List<Filter>
    ): List<DishCard> = dishes.filter { dish ->
        var result = false
        filters.forEach {
            if(result) return@forEach
            result = dish.tag_ids.contains(it.id)
        }
        result
    }.map { it }

    override suspend fun getDishesById(id: Int): DishDetails {
        val dish = foodiesStorage.getDishes()
            .first { it.id == id }
        return DishDetails(
            dish = convertFoodiesItemToDishCard(dish),
            description = dish.description,
            energy_per_100_grams = dish.energy_per_100_grams,
            proteins_per_100_grams = dish.proteins_per_100_grams,
            fats_per_100_grams = dish.fats_per_100_grams,
            carbohydrates_per_100_grams = dish.carbohydrates_per_100_grams
        )
    }

    override suspend fun getDishesByCategoryId(id: Int): List<DishCard> =
        foodiesStorage.getDishes()
            .filter { it.category_id == id }
            .map { convertFoodiesItemToDishCard(it) }

    override suspend fun getDishesByName(name: String): List<DishCard> =
        foodiesStorage.getDishes()
            .filter {
                if (name.isEmpty()) false
                else it.name.contains(name, ignoreCase = true)
            }
            .map { convertFoodiesItemToDishCard(it) }

    private fun convertFoodiesItemToDishCard(foodiesItem: FoodiesItem): DishCard =
        DishCard(
            id = foodiesItem.id,
            categoryId = foodiesItem.category_id,
            name = foodiesItem.name,
            image = foodiesItem.image,
            measure = foodiesItem.measure,
            measure_unit = foodiesItem.measure_unit,
            price_current = foodiesItem.price_current,
            price_old = foodiesItem.price_old,
            tag_ids = foodiesItem.tag_ids
        )
}
