package com.myproject.data.repository

import com.myproject.data.storage.foodies.FoodiesStorage
import com.myproject.data.storage.foodies.models.FoodiesItem
import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.dish.DishDetails
import com.myproject.domain.repository.FoodiesRepository

class FoodiesRepositoryImpl(
    private val foodiesStorage: FoodiesStorage
) : FoodiesRepository {
    override suspend fun getDishes(): List<DishCard>? = foodiesStorage.getDishes()?.map {
        convertFoodiesItemToDishCard(it)
    }

    override suspend fun getNewDishes(): List<DishCard>? = searchDishesByTag(1)

    override suspend fun getVegetarianDishes(): List<DishCard>? = searchDishesByTag(2)

    override suspend fun getHitsDishes(): List<DishCard>? = searchDishesByTag(3)

    override suspend fun getSharpDishes(): List<DishCard>? = searchDishesByTag(4)

    override suspend fun getExpressMenu(): List<DishCard>? = searchDishesByTag(5)

    override suspend fun getDishesById(id: Int): DishDetails? {
        val dish = foodiesStorage.getDishes()
            ?.first { it.id == id }
        return if (dish != null)
            DishDetails(
                id = dish.id,
                name = dish.name,
                description = dish.description,
                measure = dish.measure,
                measure_unit = dish.measure_unit,
                energy_per_100_grams = dish.energy_per_100_grams,
                proteins_per_100_grams = dish.proteins_per_100_grams,
                fats_per_100_grams = dish.fats_per_100_grams,
                carbohydrates_per_100_grams = dish.carbohydrates_per_100_grams
            )
        else
            null
    }

    override suspend fun getDishesByCategoryId(id: Int): List<DishCard>? =
        foodiesStorage.getDishes()
            ?.filter { it.category_id == id }
            ?.map { convertFoodiesItemToDishCard(it) }

    override suspend fun getDishesByName(name: String): List<DishCard>? =
        foodiesStorage.getDishes()
            ?.filter { it.name == name }
            ?.map { convertFoodiesItemToDishCard(it) }

    private suspend fun searchDishesByTag(tag: Int): List<DishCard>? =
        foodiesStorage.getDishes()
            ?.filter { dish ->
                if (dish.tag_ids.isNotEmpty()) {
                    dish.tag_ids.contains(tag)
                } else {
                    false
                }
            }
            ?.map { convertFoodiesItemToDishCard(it) }

    private fun convertFoodiesItemToDishCard(foodiesItem: FoodiesItem): DishCard =
        DishCard(
            id = foodiesItem.id,
            name = foodiesItem.name,
            image = foodiesItem.image,
            price_current = foodiesItem.price_current,
            price_old = foodiesItem.price_old
        )
}
