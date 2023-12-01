package com.myproject.foodies.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myproject.foodies.ui.theme.FoodiesTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    navigateDestination: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FoodiesTheme.colors.main),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontSize = 48.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            text = "Foodies"
        )
    }
    LaunchedEffect(Unit) {
        delay(500)
        navController.navigate(navigateDestination) {
            popUpTo(navController.currentDestination?.route.toString()) {
                inclusive = true
            }
        }
    }
}
