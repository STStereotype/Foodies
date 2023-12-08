package com.myproject.foodies.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myproject.domain.models.cart.Cart
import com.myproject.domain.models.dish.DishDetails
import com.myproject.domain.usecase.cart.SetItemUseCase
import com.myproject.domain.usecase.foodies.GetDishesByIdUseCase
import com.myproject.foodies.base.Event
import com.myproject.foodies.screens.details.models.DetailsEvent
import com.myproject.foodies.screens.details.models.DetailsViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    private val getDishesByIdUseCase: GetDishesByIdUseCase,
    private val setItemUseCase: SetItemUseCase,
    @Assisted private val id: Int?
) : ViewModel(), Event<DetailsEvent> {

    private val _viewState: MutableStateFlow<DetailsViewState> =
        MutableStateFlow(DetailsViewState.Loading)
    val viewState: StateFlow<DetailsViewState> = _viewState

    private val _dish: MutableStateFlow<DishDetails?> =
        MutableStateFlow(null)

    @AssistedFactory
    interface Factory {
        fun create(id: Int?) : DetailsViewModel
    }

    companion object {
        fun provideDetailsViewModelFactory(
            factory: Factory,
            id: Int?
        ) : ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(id) as T
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _dish.emit(getDishesByIdUseCase.execute(id!!))
        }
    }

    override fun send(event: DetailsEvent) {
        when (val state = _viewState.value) {
            is DetailsViewState.Loading -> reduce(event, state)
            is DetailsViewState.DisplayDetails -> reduce(event, state)
        }
    }

    private fun reduce(
        event: DetailsEvent,
        state: DetailsViewState.Loading
    ) {
        when (event) {
            DetailsEvent.EnterDetailsDisplay -> fetchDisplayDetails()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "DetailsEvent.EnterDetailsDisplay has no event $event"
            )
        }
    }

    private fun reduce(
        event: DetailsEvent,
        state: DetailsViewState.DisplayDetails
    ) {
        when (event) {
            DetailsEvent.EnterDetailsDisplay -> fetchDisplayDetails()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "DetailsEvent.DisplayDetails has no event $event"
            )
        }
    }

    private fun fetchDisplayDetails() {
        viewModelScope.launch {
            val viewModel = DetailsViewState.DisplayDetails(
                dish = _dish
            )
            _viewState.emit(viewModel)
        }
    }

    fun addDishToCart(idDish: Int) {
        viewModelScope.launch {
            val dish = getDishesByIdUseCase.execute(idDish)
            setItemUseCase.execute(Cart(
                dish = dish.dish,
                count = 1
            ))
        }
    }
}
