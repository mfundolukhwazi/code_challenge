package com.example.bawagcodechalenge.domain.repository

import com.example.bawagcodechalenge.domain.model.Activity

interface ActivityRepository {
    suspend fun getActivity(): Activity
    suspend fun filterActivity(type: String): List<Activity>
}