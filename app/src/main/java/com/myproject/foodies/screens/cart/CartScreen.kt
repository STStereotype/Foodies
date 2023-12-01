package com.myproject.foodies.screens.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.myproject.foodies.screens.cart.models.CartEvent
import com.myproject.foodies.screens.cart.models.CartViewState
import com.myproject.foodies.screens.cart.view.CartViewDisplay
import com.myproject.foodies.screens.cart.view.CartViewLoading
import kotlinx.coroutines.delay

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    when(val state = viewState.value) {
        CartViewState.Loading -> CartViewLoading()
        CartViewState.DisplayCart -> CartViewDisplay(
            navController = navController
        )
    }

    LaunchedEffect(viewState) {
        delay(1000)
        viewModel.send(event = CartEvent.EnterCartDisplay)
    }
}
