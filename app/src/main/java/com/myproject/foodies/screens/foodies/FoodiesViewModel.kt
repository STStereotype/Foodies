package com.myproject.foodies.screens.foodies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.foodies.base.Event
import com.myproject.foodies.screens.foodies.models.FoodiesEvent
import com.myproject.foodies.screens.foodies.models.FoodiesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodiesViewModel @Inject constructor(

) : ViewModel(), Event<FoodiesEvent> {

    private val _viewState: MutableStateFlow<FoodiesViewState> =
        MutableStateFlow(FoodiesViewState.Loading)
    val viewState: StateFlow<FoodiesViewState> = _viewState

    override fun send(event: FoodiesEvent) {
        when (val state = _viewState.value) {
            is FoodiesViewState.Loading -> reduce(event, state)
            is FoodiesViewState.DisplayFoodies -> reduce(event, state)
        }
    }

    private fun reduce(
        event: FoodiesEvent,
        state: FoodiesViewState.Loading
    ) {
        when (event) {
            FoodiesEvent.EnterFoodiesDisplay -> fetchDisplayFoodies()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "FoodiesViewState.Loading has no event $event"
            )
        }
    }

    private fun reduce(
        event: FoodiesEvent,
        state: FoodiesViewState.DisplayFoodies
    ) {
        when (event) {
            FoodiesEvent.EnterFoodiesDisplay -> fetchDisplayFoodies()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "FoodiesViewState.DisplayFoodies has no event $event"
            )
        }
    }

    private fun fetchDisplayFoodies() {
        viewModelScope.launch {
            _viewState.emit(FoodiesViewState.DisplayFoodies)
        }
    }
}
