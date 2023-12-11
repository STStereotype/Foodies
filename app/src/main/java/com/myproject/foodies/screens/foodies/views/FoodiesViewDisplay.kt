package com.myproject.foodies.screens.foodies.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myproject.foodies.screens.foodies.models.viewstate.displayfoodiesstate.FoodiesState
import com.myproject.foodies.screens.foodies.widgets.cart.CartButton
import com.myproject.foodies.screens.foodies.widgets.foodies.ListDish
import com.myproject.foodies.ui.theme.FoodiesTheme

@Composable
fun FoodiesViewDisplay(
    foodiesState: FoodiesState,
    onDish: (id: Int) -> Unit,
    onIncrease: (id: Int) -> Unit,
    onDecrease: (id: Int) -> Unit,
    onCart: () -> Unit
) {
    val lazyGridState = foodiesState.lazyGridState.collectAsState().value
    val listEmpty = foodiesState.informationText.collectAsState().value
    val dishes = foodiesState.dishes.collectAsState().value
    val costDish = foodiesState.costDish.collectAsState().value
    val idAndCountDishesInCart = foodiesState.idAndCountDishesInCart.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (dishes.isNotEmpty())
            ListDish(
                lazyGridState = lazyGridState,
                dishes = dishes,
                onDish = onDish,
                idAndCountDishesInCart = idAndCountDishesInCart,
                onIncrease = { onIncrease.invoke(it) },
                onDecrease = { onDecrease.invoke(it) }
            )
        else Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                style = FoodiesTheme.typography.listEmpty,
                text = listEmpty,
            )
        }
        if (costDish != 0)
            CartButton(
                icon = true,
                textButton = "$costDish ₽",
                onCart = onCart
            )
    }
}
