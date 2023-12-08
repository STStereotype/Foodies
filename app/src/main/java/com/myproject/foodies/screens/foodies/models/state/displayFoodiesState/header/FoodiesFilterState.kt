package com.myproject.foodies.screens.foodies.models.state.displayFoodiesState.header

import com.myproject.domain.models.filter.Filter
import kotlinx.coroutines.flow.StateFlow

data class FoodiesFilterState(
    val isFilter: StateFlow<Boolean>,
    val filters: StateFlow<List<Filter>>,
    val activeFilters: StateFlow<List<Filter>>,
    val openFilter: () -> Unit,
    val closeFilter: (List<Filter>?) -> Unit
)