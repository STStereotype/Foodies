package com.myproject.foodies.screens.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myproject.foodies.ui.theme.FoodiesTheme

@Composable
fun DetailsViewLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FoodiesTheme.colors.white)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = FoodiesTheme.colors.main
        )
    }
}

