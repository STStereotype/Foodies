package com.myproject.foodies.screens.foodies.widgets.foodies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.myproject.foodies.R
import com.myproject.foodies.ui.theme.FoodiesTheme

@Composable
fun DishItem(
    title: String,
    measure: String,
    measureUnit: String,
    priceCurrent: Int,
    priceOld: Int? = null,
    onDetails: () -> Unit,
    count: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Card(
        shape = FoodiesTheme.shapes.button,
        colors = CardDefaults.cardColors(
            containerColor = FoodiesTheme.colors.lightGray
        ),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Box {
            if(priceOld != null)
                Image(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp),
                    painter = painterResource(id = R.drawable.ic_sale),
                    contentDescription = null
                )
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.dish),
                contentDescription = null
            )
        }
        Column (
            modifier = Modifier
                .padding(12.dp)
        ) {
            Column {
                Text(
                    style = FoodiesTheme.typography.secondaryDark,
                    text = title,
                    maxLines = 1,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    style = FoodiesTheme.typography.secondaryGray,
                    text = "$measure $measureUnit"
                )
            }
            if (count > 0) {
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .size(44.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(FoodiesTheme.colors.white),
                        shape = FoodiesTheme.shapes.button,
                        elevation = null,
                        onClick = onDecrease
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            tint = FoodiesTheme.colors.main,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "$count",
                        style = FoodiesTheme.typography.primaryDark
                    )
                    Button(
                        modifier = Modifier
                            .size(44.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(FoodiesTheme.colors.white),
                        shape = FoodiesTheme.shapes.button,
                        elevation = null,
                        onClick = onIncrease
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_plus),
                            tint = FoodiesTheme.colors.main,
                            contentDescription = null
                        )
                    }
                }
            } else {
                Button(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    colors = ButtonDefaults.buttonColors(FoodiesTheme.colors.white),
                    shape = FoodiesTheme.shapes.button,
                    elevation = null,
                    onClick = onDetails
                ) {
                    Text(
                        text = "$priceCurrent ₽",
                        style = FoodiesTheme.typography.primaryDark
                    )
                    if (priceOld != null) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = "$priceOld ₽",
                            style = FoodiesTheme.typography.secondaryGray.copy(
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }
                }
            }
        }
    }
}