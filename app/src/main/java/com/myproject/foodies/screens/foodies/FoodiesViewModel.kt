package com.myproject.foodies.screens.foodies

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.domain.models.cart.Cart
import com.myproject.domain.models.categoty.Category
import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.models.filter.Filter
import com.myproject.domain.usecase.cart.GetCartUseCase
import com.myproject.domain.usecase.cart.RemoveItemUseCase
import com.myproject.domain.usecase.cart.SetItemUseCase
import com.myproject.domain.usecase.category.GetCategoriesUseCase
import com.myproject.domain.usecase.filter.GetFiltersUseCase
import com.myproject.domain.usecase.foodies.GetDishesByCategoryIdUseCase
import com.myproject.domain.usecase.foodies.GetDishesByFiltersUseCase
import com.myproject.domain.usecase.foodies.GetDishesByNameUseCase
import com.myproject.foodies.base.Event
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.screens.foodies.models.FoodiesEvent
import com.myproject.foodies.screens.foodies.models.viewstate.FoodiesViewState
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header.FoodiesCategoryState
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.DisplayFoodiesState
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header.FoodiesFilterState
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.FoodiesState
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header.FoodiesHeaderState
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.header.FoodiesSearchState
import com.myproject.foodies.screens.foodies.models.viewstate.displaysearchstate.DisplaySearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodiesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getFilterByCategoryIdUseCase: GetDishesByCategoryIdUseCase,
    private val getDishesByFiltersUseCase: GetDishesByFiltersUseCase,
    private val getDishesByNameUseCase: GetDishesByNameUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val setItemUseCase: SetItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase
) : ViewModel(), Event<FoodiesEvent> {

    private val _viewState: MutableStateFlow<FoodiesViewState> =
        MutableStateFlow(FoodiesViewState.Loading)
    val viewState: StateFlow<FoodiesViewState> = _viewState

    // Categories
    private val _categories: MutableStateFlow<List<Category>> =
        MutableStateFlow(listOf())
    private val _selectedCategory: MutableStateFlow<Int> =
        MutableStateFlow(0)
    private val _scrollStateCategory: MutableStateFlow<ScrollState> =
        MutableStateFlow(ScrollState(0))

    // Dishes
    private val _dishes: MutableStateFlow<List<DishCard>> =
        MutableStateFlow(listOf())
    private val _listEmpty: MutableStateFlow<String> =
        MutableStateFlow("")
    private val _lazyListStateProducts: MutableStateFlow<LazyGridState> =
        MutableStateFlow(LazyGridState())

    // Filters
    private val _filters: MutableStateFlow<List<Filter>> =
        MutableStateFlow(listOf())
    private val _activeFilters: MutableStateFlow<List<Filter>> =
        MutableStateFlow(listOf())
    private val _isFilter: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    private val _cart: MutableStateFlow<List<Cart>> =
        MutableStateFlow(listOf())
    private val _idAndCountDishesInCart: MutableStateFlow<Map<Int, Int>> =
        MutableStateFlow(mapOf())
    private val _costDish: MutableStateFlow<Int> =
        MutableStateFlow(0)
    private val _nameDishToSearch: MutableStateFlow<String> =
        MutableStateFlow("")

    init {
        viewModelScope.launch {
            _categories.emit(getCategoriesUseCase.execute())
            if (_categories.value.isNotEmpty()) _selectedCategory.emit(_categories.value[0].id)
            filteringDishes(_selectedCategory.value)
            _filters.emit(getFiltersUseCase.execute())
        }
    }

    override fun send(event: FoodiesEvent) {
        when (val state = _viewState.value) {
            is FoodiesViewState.Loading -> reduce(event, state)
            is FoodiesViewState.FoodiesDisplay -> reduce(event, state)
            is FoodiesViewState.SearchDisplay -> reduce(event, state)
        }
    }

    private fun reduce(
        event: FoodiesEvent,
        state: FoodiesViewState.Loading
    ) {
        when (event) {
            FoodiesEvent.EnterFoodiesDisplay -> fetchFoodiesDisplay()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "FoodiesViewState.Loading has no event $event"
            )
        }
    }

    private fun reduce(
        event: FoodiesEvent,
        state: FoodiesViewState.FoodiesDisplay
    ) {
        when (event) {
            FoodiesEvent.EnterFoodiesDisplay -> fetchFoodiesDisplay()
            FoodiesEvent.EnterSearchDisplay -> fetchSearchDisplay()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "FoodiesViewState.FoodiesDisplay has no event $event"
            )
        }
    }

    private fun reduce(
        event: FoodiesEvent,
        state: FoodiesViewState.SearchDisplay
    ) {
        when (event) {
            FoodiesEvent.EnterSearchDisplay -> fetchSearchDisplay()
            FoodiesEvent.EnterFoodiesDisplay -> fetchFoodiesDisplay()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "FoodiesViewState.SearchDisplay has no event $event"
            )
        }
    }

    private fun fetchFoodiesDisplay() {
        viewModelScope.launch {
            filteringDishes(_selectedCategory.value)
            _listEmpty.emit("")
            updateCart()
            val viewState = FoodiesViewState.FoodiesDisplay(
                displayFoodiesState = DisplayFoodiesState(
                    foodiesHeaderState = FoodiesHeaderState(
                        foodiesCategoryState = FoodiesCategoryState(
                            idSelectedCategory = _selectedCategory,
                            categoryFeed = _categories,
                            scrollState = _scrollStateCategory,
                            onCategory = { id -> changeSelectedCategory(id) }
                        ),
                        foodiesFilterState = FoodiesFilterState(
                            isFilter = _isFilter,
                            filters = _filters,
                            activeFilters = _activeFilters,
                            openFilter = {
                                viewModelScope.launch { _isFilter.emit(true) }
                            },
                            closeFilter = { filters ->
                                viewModelScope.launch {
                                    if (filters != null) _activeFilters.emit(filters)
                                    _isFilter.emit(false)
                                    filteringDishes(_selectedCategory.value)
                                }
                            }
                        ),
                        foodiesSearchState = FoodiesSearchState(
                            onSearch = {
                                viewModelScope.launch {
                                    send(FoodiesEvent.EnterSearchDisplay)
                                }
                            }
                        )
                    ),
                    foodiesState = FoodiesState(
                        dishes = _dishes,
                        idAndCountDishesInCart = _idAndCountDishesInCart,
                        costDish = _costDish,
                        informationText = _listEmpty,
                        lazyGridState = _lazyListStateProducts,
                        onDish = { navController, id ->
                            val destinations = FoodiesGraphDestinations.Details.destination
                                .substringBefore("{")
                            navController.navigate("$destinations$id")
                        },
                        onIncrease = { id ->
                            viewModelScope.launch {
                                val dish = _cart.value.first { id == it.dish.id }
                                setItemUseCase.execute(dish)
                                updateCart()
                            }
                        },
                        onDecrease = { id ->
                            viewModelScope.launch {
                                removeItemUseCase.execute(id)
                                updateCart()
                            }
                        },
                        onCart = { navController ->
                            val destination = FoodiesGraphDestinations.Cart.destination
                            navController.navigate(destination)
                        }
                    )
                ),
            )
            _viewState.emit(viewState)
        }
    }

    private fun fetchSearchDisplay() {
        viewModelScope.launch {
            _dishes.emit(listOf())
            _listEmpty.emit(
                "Введите название блюда, \n" +
                        "которое ищете"
            )
            updateCart()
            val viewState = FoodiesViewState.SearchDisplay(
                displaySearchState = DisplaySearchState(
                    dishes = _dishes,
                    idAndCountDishesInCart = _idAndCountDishesInCart,
                    informationText = _listEmpty,
                    onDish = { navController, id ->
                        val destinations = FoodiesGraphDestinations.Details.destination
                            .substringBefore("{")
                        navController.navigate("$destinations$id")
                    },
                    onSearchDish = { name ->
                        viewModelScope.launch {
                            val dishes = getDishesByNameUseCase.execute(name)
                            if (dishes.isEmpty()) _dishes.emit(listOf())
                            else _dishes.emit(dishes)
                        }
                    },
                    onIncrease = { id ->
                        viewModelScope.launch {
                            val dish = _cart.value.first { id == it.dish.id }
                            setItemUseCase.execute(dish)
                            updateCart()
                        }
                    },
                    onDecrease = { id ->
                        viewModelScope.launch {
                            removeItemUseCase.execute(id)
                            updateCart()
                        }
                    },
                    onBack = {
                        viewModelScope.launch {
                            send(FoodiesEvent.EnterFoodiesDisplay)
                            _dishes.emit(listOf())
                        }
                    }
                )
            )
            _viewState.emit(viewState)
        }
    }

    private fun changeSelectedCategory(id: Int) {
        viewModelScope.launch {
            _selectedCategory.emit(id)
            filteringDishes(_selectedCategory.value)
            _lazyListStateProducts.emit(LazyGridState())
        }
    }

    private suspend fun filteringDishes(id: Int) {
        // Filtering by category
        val filteredDishes = getFilterByCategoryIdUseCase.execute(id)
        if (filteredDishes.isEmpty()) {
            _dishes.emit(listOf())
            _listEmpty.emit("Пусто, выберите блюда из другой категории :)")
            return
        }
        if (_activeFilters.value.isEmpty()) {
            _dishes.emit(filteredDishes)
            return
        }
        // Filtering by filters
        _dishes.emit(getDishesByFiltersUseCase.execute(filteredDishes, _activeFilters.value))
        if (_dishes.value.isEmpty()) {
            _listEmpty.emit("Таких блюд нет :(\nПопробуйте изменить фильтры")
            return
        }
    }

    private suspend fun updateCart() {
        _cart.emit(getCartUseCase.execute())
        if (_cart.value.isNotEmpty()) {
            var costDish = 0
            _cart.value.forEach { dish ->
                costDish += dish.dish.price_current * dish.count
            }
            _costDish.emit(costDish)
            _idAndCountDishesInCart.emit(_cart.value.associate {
                Pair(it.dish.id, it.count)
            })
        } else {
            _costDish.emit(0)
            _idAndCountDishesInCart.emit(mapOf())
        }
    }
}
