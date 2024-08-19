package com.example.bawagcodechalenge.presentation

sealed class Routes(val route: String) {
    data object ActivityScreen : Routes(route = "activity")
}