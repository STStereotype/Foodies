package com.myproject.foodies.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.foodies.base.Event
import com.myproject.foodies.screens.cart.models.CartEvent
import com.myproject.foodies.screens.cart.models.CartViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(

) : ViewModel(), Event<CartEvent> {

    private val _viewState: MutableStateFlow<CartViewState> =
        MutableStateFlow(CartViewState.Loading)
    val viewState: StateFlow<CartViewState> = _viewState

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
            _viewState.emit(CartViewState.DisplayCart)
        }
    }
}
