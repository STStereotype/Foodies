package com.myproject.foodies.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.foodies.base.Event
import com.myproject.foodies.screens.details.models.DetailsEvent
import com.myproject.foodies.screens.details.models.DetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(

) : ViewModel(), Event<DetailsEvent> {

    private val _viewState: MutableStateFlow<DetailsViewState> =
        MutableStateFlow(DetailsViewState.Loading)
    val viewState: StateFlow<DetailsViewState> = _viewState

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
            _viewState.emit(DetailsViewState.DisplayDetails)
        }
    }
}
