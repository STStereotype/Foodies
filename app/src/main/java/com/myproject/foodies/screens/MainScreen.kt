package com.myproject.foodies.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.myproject.foodies.navigation.NavGraphTabs
import com.myproject.foodies.navigation.tabs.foodiesGraph

@Composable
fun MainScreen() {
    val childNavController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = childNavController,
            startDestination = NavGraphTabs.Foodies.route
        ) {
            foodiesGraph(childNavController)
        }
    }
}
