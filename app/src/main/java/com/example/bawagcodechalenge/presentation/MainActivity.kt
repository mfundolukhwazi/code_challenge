package com.example.bawagcodechalenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bawagcodechalenge.presentation.activities.ActivityViewModel
import com.example.bawagcodechalenge.presentation.activities.components.ActivityScreen
import com.example.bawagcodechalenge.presentation.theme.BawagCodeChalengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BawagCodeChalengeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController();
                    NavHost(
                        navController = navController,
                        startDestination = Routes.ActivityScreen.route
                    ) {
                        composable(route = Routes.ActivityScreen.route) {
                            val activityViewModel = hiltViewModel<ActivityViewModel>()
                            ActivityScreen(viewModel = activityViewModel)
                        }
                    }
                }
            }
        }
    }
}