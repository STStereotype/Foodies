package com.myproject.foodies.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.myproject.foodies.R

data class FoodiesColors(
    val main: Color,
    val white: Color,
    val black: Color,
    val black12: Color,
    val gray: Color,
    val lightGray: Color
)

data class FoodiesTypography(
    val primaryWhite: TextStyle,
    val primaryDark: TextStyle,
    val crossesGray: TextStyle,
    val listEmpty: TextStyle,
    val secondaryDark: TextStyle,
    val secondaryGray: TextStyle,
    val bigTitle: TextStyle,
    val description: TextStyle,
    val nameParam: TextStyle,
    val valueParam: TextStyle,
    val searchPlaceholder: TextStyle,
    val searchValue: TextStyle,
    val message: TextStyle,
    val titleBottomSheet: TextStyle,
    val nameParamBottomSheet: TextStyle,
    val countActiveFilters: TextStyle
)

data class FoodiesShape(
    val button: Shape,
    val bottomSheet: Shape,
)

object FoodiesTheme {
    val colors: FoodiesColors
        @Composable
        get() = LocalFoodiesColors.current

    val typography: FoodiesTypography
        @Composable
        get() = LocalFoodiesTypography.current

    val shapes: FoodiesShape
        @Composable
        get() = LocalFoodiesShape.current
}

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium)
)

val Inter = FontFamily(
    Font(R.font.inter_semibold, FontWeight.SemiBold)
)

val LocalFoodiesColors = staticCompositionLocalOf<FoodiesColors> {
    error("No colors provided")
}

val LocalFoodiesTypography = staticCompositionLocalOf<FoodiesTypography> {
    error("No font provided")
}

val LocalFoodiesShape = staticCompositionLocalOf<FoodiesShape> {
    error("No shapes provided")
}