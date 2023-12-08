package com.myproject.foodies.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

private val patternBlackTextStyle = TextStyle(
    fontFamily = Roboto,
    fontSize = 16.sp,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Medium,
    color = BaseLightPalette.black
)

val Typography = FoodiesTypography(
    primaryWhite = patternBlackTextStyle.copy(color = BaseLightPalette.white),
    primaryDark = patternBlackTextStyle,
    crossesGray = patternBlackTextStyle.copy(color = BaseLightPalette.gray),
    secondaryDark = patternBlackTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    secondaryGray = patternBlackTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = BaseLightPalette.gray
    ),
    listEmpty = patternBlackTextStyle.copy(
        fontWeight = FontWeight.Normal,
        color = BaseLightPalette.gray,
        textAlign = TextAlign.Center,
    ),
    bigTitle = patternBlackTextStyle.copy(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal
    ),
    description = patternBlackTextStyle.copy(
        fontWeight = FontWeight.Normal,
        color = BaseLightPalette.gray
    ),
    nameParam = patternBlackTextStyle.copy(
        fontWeight = FontWeight.Normal,
        color = BaseLightPalette.gray
    ),
    valueParam = patternBlackTextStyle.copy(
        fontWeight = FontWeight.Normal
    ),
    searchPlaceholder = patternBlackTextStyle.copy(
        fontFamily = Inter,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    searchValue = patternBlackTextStyle.copy(
        fontWeight = FontWeight.Normal
    ),
    message = patternBlackTextStyle.copy(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal
    ),
    titleBottomSheet = patternBlackTextStyle.copy(
        fontSize = 20.sp,
    ),
    nameParamBottomSheet = patternBlackTextStyle.copy(
        fontWeight = FontWeight.Normal
    ),
    countActiveFilters = patternBlackTextStyle.copy(
        color = BaseLightPalette.white,
        fontSize = 11.sp
    ),
)