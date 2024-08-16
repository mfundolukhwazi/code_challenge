package com.example.bawagcodechalenge.presentation.activities

import com.example.bawagcodechalenge.domain.model.Activity

data class ActivityState(
    val isLoading: Boolean = false,
    val activity: Activity? = null,
    val error: String = "",
    val filter: String? = null
)