package com.myproject.foodies.screens.cart.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myproject.domain.models.cart.Cart

@Composable
fun ListCart(
    carts: List<Cart>,
    onIncrease: (id: Int) -> Unit,
    onDecrease: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(carts.size) { index ->
            val cart = carts[index]
            val dish = cart.dish
            val count = cart.count

            CartItem(
                title = dish.name,
                priceCurrent = dish.price_current,
                priceOld = dish.price_old,
                count = count,
                onIncrease =  { onIncrease.invoke(dish.id) },
                onDecrease = { onDecrease.invoke(dish.id) }
            )
        }
    }
}
