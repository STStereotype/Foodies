package com.myproject.foodies.screens.foodies.widgets.cart

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myproject.foodies.R
import com.myproject.foodies.ui.theme.FoodiesTheme

@Composable
fun CartButton(
    icon: Boolean = false,
    textButton: String,
    onCart: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(FoodiesTheme.colors.white)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = FoodiesTheme.colors.main
            ),
            shape = FoodiesTheme.shapes.button,
            onClick = onCart,
            contentPadding = PaddingValues(16.dp)
        ) {
            if (icon)
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null
                )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                style = FoodiesTheme.typography.primaryWhite,
                text = textButton,
                textAlign = TextAlign.Center
            )
        }
    }
}
