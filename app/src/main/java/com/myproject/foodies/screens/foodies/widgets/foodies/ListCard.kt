package com.myproject.foodies.screens.foodies.widgets.foodies

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myproject.domain.models.dish.DishCard

@Composable
fun ColumnScope.ListDish(
    lazyGridState: LazyGridState,
    dishes: List<DishCard>,
    onDish: (id: Int) -> Unit,
    idAndCountDishesInCart: Map<Int, Int>,
    onIncrease: (id: Int) -> Unit,
    onDecrease: (id: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .weight(1f),
        state = lazyGridState,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(dishes.size) { position ->
                val measure =
                    if (dishes[position].measure == null) ""
                    else dishes[position].measure.toString()

                val measureUnit =
                    if (dishes[position].measure_unit == null) ""
                    else dishes[position].measure_unit.toString()

                val id = dishes[position].id

                DishItem(
                    title = dishes[position].name,
                    measure = measure,
                    measureUnit = measureUnit,
                    priceCurrent = dishes[position].price_current,
                    priceOld = dishes[position].price_old,
                    onDetails = { onDish(id) },
                    count = idAndCountDishesInCart[id] ?: 0,
                    onIncrease = { onIncrease.invoke(id) },
                    onDecrease = { onDecrease.invoke(id) }
                )
            }
        }
    )
}
