package com.example.bawagcodechalenge.presentation

sealed class Screen(val route: String) {
    data object ActivityScreen: Screen(route = "activity")
}