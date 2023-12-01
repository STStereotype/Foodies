package com.myproject.foodies.screens.details.models

sealed class DetailsViewState {
    object Loading: DetailsViewState()
    object DisplayDetails: DetailsViewState()
}
