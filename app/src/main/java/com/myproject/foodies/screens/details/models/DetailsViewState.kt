package com.myproject.foodies.screens.details.models

import com.myproject.domain.models.dish.DishDetails
import kotlinx.coroutines.flow.StateFlow

sealed class DetailsViewState {
    object Loading: DetailsViewState()
    data class DisplayDetails(
        val dish: StateFlow<DishDetails>
    ): DetailsViewState()
}
