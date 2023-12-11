package com.myproject.foodies.screens.cart.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myproject.foodies.R
import com.myproject.foodies.screens.cart.models.viewstate.CartViewState
import com.myproject.foodies.screens.cart.widgets.ListCart
import com.myproject.foodies.screens.foodies.widgets.cart.CartButton
import com.myproject.foodies.ui.theme.FoodiesTheme
import com.myproject.foodies.ui.theme.modifier.shadow

@Composable
fun CartViewDisplay(
    cartState: CartViewState.DisplayCart,
    navController: NavController
) {
    val state = cartState.displayCartState
    val cart = cartState.displayCartState.cart.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    color = Color(0x201F1F1F),
                )
                .background(color = FoodiesTheme.colors.white)
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {
                state.onBack.invoke(navController)
            }) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    tint = FoodiesTheme.colors.main,
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .weight(1f),
                style = FoodiesTheme.typography.searchPlaceholder,
                text = "Корзина"
            )
        }
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (cart.isEmpty()) {
                Text(
                    style = FoodiesTheme.typography.listEmpty,
                    text = "Пусто, выберите блюда\nв каталоге :)"
                )
            } else {
                ListCart(
                    carts = cart,
                    onIncrease = state.onIncrease,
                    onDecrease = state.onDecrease
                )
            }
        }
        if (cart.isNotEmpty())
            CartButton(
                textButton = "Заказать за ${state.costDish.value} ₽",
                onCart = state.placeAnOrder
            )
    }
}
