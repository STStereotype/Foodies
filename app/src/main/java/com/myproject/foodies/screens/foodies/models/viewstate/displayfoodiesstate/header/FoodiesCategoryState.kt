package com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header

import androidx.compose.foundation.ScrollState
import com.myproject.domain.models.categoty.Category
import kotlinx.coroutines.flow.StateFlow

data class FoodiesCategoryState(
    val idSelectedCategory: StateFlow<Int>,
    val categoryFeed: StateFlow<List<Category>>,
    val scrollState: StateFlow<ScrollState>,
    val onCategory: (Int) -> Unit
)