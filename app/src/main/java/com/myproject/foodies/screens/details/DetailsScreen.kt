package com.myproject.foodies.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.myproject.foodies.screens.details.models.DetailsEvent
import com.myproject.foodies.screens.details.models.DetailsViewState
import com.myproject.foodies.screens.details.view.DetailsViewDisplay
import com.myproject.foodies.screens.details.view.DetailsViewLoading
import kotlinx.coroutines.delay

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    when (val state = viewState.value) {
        DetailsViewState.Loading -> DetailsViewLoading()
        DetailsViewState.DisplayDetails -> DetailsViewDisplay(
            navController = navController
        )
    }

    LaunchedEffect(viewState) {
        delay(1000)
        viewModel.send(event = DetailsEvent.EnterDetailsDisplay)
    }
}
