package com.myproject.foodies.navigation.destination

sealed class FoodiesGraphDestinations(val destination: String) {
    object Foodies: FoodiesGraphDestinations("foodiesNav_FoodiesScreen")
    object Details: FoodiesGraphDestinations("foodiesNav_DetailsScreen")
    object Cart: FoodiesGraphDestinations("foodiesNav_CartScreen")
}
