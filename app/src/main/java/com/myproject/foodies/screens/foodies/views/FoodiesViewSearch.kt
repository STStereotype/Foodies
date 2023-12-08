package com.myproject.foodies.screens.foodies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myproject.foodies.R
import com.myproject.foodies.screens.foodies.models.state.displaySearchState.DisplaySearchState
import com.myproject.foodies.screens.foodies.widgets.foodies.ListDish
import com.myproject.foodies.ui.theme.FoodiesTheme
import com.myproject.foodies.ui.theme.modifier.shadow

@Composable
fun FoodiesViewSearch(
    displaySearchState: DisplaySearchState,
    onDish: (id: Int) -> Unit,
    onIncrease: (id: Int) -> Unit,
    onDecrease: (id: Int) -> Unit,
) {
    val dishes = displaySearchState.dishes.collectAsState().value
    val informationText = displaySearchState.informationText.collectAsState().value
    val idAndCountDishesInCart = displaySearchState.idAndCountDishesInCart.collectAsState().value

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
            val textValue = remember {
                mutableStateOf("")
            }
            IconButton(onClick = displaySearchState.onBack) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    tint = FoodiesTheme.colors.main,
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
            TextField(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                    displaySearchState.onSearchDish.invoke(textValue.value)
                },
                placeholder = {
                    Text(
                        style = FoodiesTheme.typography.searchValue,
                        text = "Найти блюдо"
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = FoodiesTheme.colors.gray,
                    focusedPlaceholderColor = FoodiesTheme.colors.gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                textStyle = FoodiesTheme.typography.searchPlaceholder
            )
        }
        if (dishes.isNotEmpty())
            ListDish(
                lazyGridState = LazyGridState(),
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
                text = informationText,
            )
        }
    }
}
