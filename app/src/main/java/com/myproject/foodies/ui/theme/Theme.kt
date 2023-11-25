package com.myproject.foodies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) BaseLightPalette else BaseLightPalette
    val typography = Typography
    val shape = Shapes

    CompositionLocalProvider (
        LocalFoodiesColors provides colors,
        LocalFoodiesTypography provides typography,
        LocalFoodiesShape provides shape,
        content = content
    )
}