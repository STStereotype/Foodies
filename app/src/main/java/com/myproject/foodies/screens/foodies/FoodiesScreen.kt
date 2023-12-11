package com.myproject.foodies.screens.foodies

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.myproject.foodies.screens.foodies.models.FoodiesEvent
import com.myproject.foodies.screens.foodies.models.viewstate.FoodiesViewState
import com.myproject.foodies.screens.foodies.views.FoodiesViewDisplay
import com.myproject.foodies.screens.foodies.views.FoodiesViewLoading
import com.myproject.foodies.screens.foodies.views.FoodiesViewSearch
import com.myproject.foodies.screens.foodies.widgets.header.BottomSheet
import com.myproject.foodies.screens.foodies.widgets.header.FoodiesWidgetsHeader

@Composable
fun FoodiesScreen(
    navController: NavController,
    viewModel: FoodiesViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    val enabledBackHandler = remember {
        mutableStateOf(false)
    }

    BackHandler(
        enabled = enabledBackHandler.value
    ) {
        when (val state = viewState.value) {
            is FoodiesViewState.SearchDisplay -> {
                state.displaySearchState.onBack.invoke()
                enabledBackHandler.value = false
            }
            else -> {}
        }
    }

    when (val state = viewState.value) {
        FoodiesViewState.Loading -> FoodiesViewLoading()
        is FoodiesViewState.FoodiesDisplay -> DisplayFoodies(
            navController = navController,
            state = state
        )
        is FoodiesViewState.SearchDisplay -> {
            enabledBackHandler.value = true
            DisplaySearch(
                navController = navController,
                state = state
            )
        }
    }

    LaunchedEffect(viewState) {
        when (val state = viewState.value) {
            FoodiesViewState.Loading -> {
                viewModel.send(event = FoodiesEvent.EnterFoodiesDisplay)
            }
            is FoodiesViewState.FoodiesDisplay -> {
                viewModel.send(event = FoodiesEvent.EnterFoodiesDisplay)
            }
            is FoodiesViewState.SearchDisplay -> {
                viewModel.send(event = FoodiesEvent.EnterSearchDisplay)
            }
        }
    }
}

@Composable
private fun DisplayFoodies(
    navController: NavController,
    state: FoodiesViewState.FoodiesDisplay
) {
    val headerState = state.displayFoodiesState.foodiesHeaderState

    val foodiesState = state.displayFoodiesState.foodiesState

    val filterState = headerState.foodiesFilterState
    val isFilter = filterState.isFilter.collectAsState().value
    val filters = filterState.filters.collectAsState().value
    val activeFilters = filterState.activeFilters.collectAsState().value

    val searchState = headerState.foodiesSearchState

    Column {
        FoodiesWidgetsHeader(
            headerState = headerState,
            onFilter = { filterState.openFilter.invoke() },
            onSearch = { searchState.onSearch.invoke(navController) }
        )
        FoodiesViewDisplay(
            foodiesState = foodiesState,
            onDish = { foodiesState.onDish.invoke(navController, it) },
            onIncrease = { foodiesState.onIncrease.invoke(it) },
            onDecrease = { foodiesState.onDecrease.invoke(it) },
            onCart = { foodiesState.onCart.invoke(navController) }
        )
    }

    if (isFilter) {
        BottomSheet(
            filters = filters,
            activeFilters = activeFilters,
            onClose = { filterState.closeFilter.invoke(null) },
            onSubmit = { filterState.closeFilter.invoke(it) }
        )
    }
}

@Composable
private fun DisplaySearch(
    navController: NavController,
    state: FoodiesViewState.SearchDisplay
) {
    val displaySearchState = state.displaySearchState

    FoodiesViewSearch(
        displaySearchState = displaySearchState,
        onDish = { displaySearchState.onDish.invoke(navController, it) },
        onIncrease = { displaySearchState.onIncrease.invoke(it) },
        onDecrease = { displaySearchState.onDecrease.invoke(it) },
    )
}
