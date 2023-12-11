package com.myproject.foodies.screens.cart.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
fun CartItem(
    title: String,
    priceCurrent: Int,
    priceOld: Int? = null,
    count: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(96.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.dish),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
                .height(96.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                style = FoodiesTheme.typography.secondaryDark,
                text = title,
                minLines = 2,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .size(44.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(FoodiesTheme.colors.lightGray),
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
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        text = "$count",
                        style = FoodiesTheme.typography.primaryDark
                    )
                    Button(
                        modifier = Modifier
                            .size(44.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(FoodiesTheme.colors.lightGray),
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
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "$priceCurrent ₽",
                        style = FoodiesTheme.typography.primaryDark
                    )
                    if (priceOld != null) {
                        Text(
                            modifier = Modifier
                                .padding(top = 2.dp),
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
    Divider(color = FoodiesTheme.colors.black12, thickness = 1.dp)
}
