package com.myproject.foodies.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.myproject.domain.models.cart.Cart
import com.myproject.domain.usecase.cart.GetCartUseCase
import com.myproject.domain.usecase.cart.PlaceAnOrderUseCase
import com.myproject.domain.usecase.cart.RemoveItemUseCase
import com.myproject.domain.usecase.cart.SetItemUseCase
import com.myproject.foodies.base.Event
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.screens.cart.models.CartEvent
import com.myproject.foodies.screens.cart.models.viewstate.CartViewState
import com.myproject.foodies.screens.cart.models.viewstate.displaycartstate.DisplayCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val placeAnOrderUseCase: PlaceAnOrderUseCase,
    private val setItemUseCase: SetItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase
) : ViewModel(), Event<CartEvent> {

    private val _viewState: MutableStateFlow<CartViewState> =
        MutableStateFlow(CartViewState.Loading)
    val viewState: StateFlow<CartViewState> = _viewState

    private val _cart: MutableStateFlow<List<Cart>> =
        MutableStateFlow(listOf())

    private val _costDish: MutableStateFlow<Int> =
        MutableStateFlow(0)

    init {
        viewModelScope.launch {
            updateCart()
        }
    }
    override fun send(event: CartEvent) {
        when (val state = _viewState.value) {
            is CartViewState.Loading -> reduce(event, state)
            is CartViewState.DisplayCart -> reduce(event, state)
        }
    }

    private fun reduce(
        event: CartEvent,
        state: CartViewState.Loading
    ) {
        when (event) {
            CartEvent.EnterCartDisplay -> fetchDisplayCart()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "CartEvent.EnterCartDisplay has no event $event"
            )
        }
    }

    private fun reduce(
        event: CartEvent,
        state: CartViewState.DisplayCart
    ) {
        when (event) {
            CartEvent.EnterCartDisplay -> fetchDisplayCart()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "CartEvent.DisplayCart has no event $event"
            )
        }
    }

    private fun fetchDisplayCart() {
        viewModelScope.launch {
            val viewState = CartViewState.DisplayCart(
                displayCartState = DisplayCartState(
                    cart = _cart,
                    costDish = _costDish,
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
                    placeAnOrder = {
                        viewModelScope.launch {
                            placeAnOrderUseCase.execute(_cart.value)
                        }
                    },
                    onBack = { navController ->
                        navController.navigate(
                            route = FoodiesGraphDestinations.Foodies.destination,
                            navOptions = navOptions {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        )
                    }
                )
            )
            _viewState.emit(viewState)
        }
    }
    private suspend fun updateCart() {
        _cart.emit(getCartUseCase.execute())
        _costDish.emit(if (_cart.value.isNotEmpty()) {
            var costDish = 0
            _cart.value.forEach { dish ->
                costDish += dish.dish.price_current * dish.count
            }
            costDish
        } else 0)
    }
}
