package com.myproject.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.myproject.foodies.navigation.NavGraphTabs
import com.myproject.foodies.navigation.tabs.mainGraph
import com.myproject.foodies.screens.details.DetailsViewModel
import com.myproject.foodies.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavGraphTabs.Main.route
                ) {
                    mainGraph(navController)
                }
            }
        }
    }
}
