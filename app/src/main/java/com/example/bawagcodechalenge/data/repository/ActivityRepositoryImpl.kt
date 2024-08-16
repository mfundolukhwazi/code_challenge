package com.example.bawagcodechalenge.data.repository

import com.example.bawagcodechalenge.data.remote.BoredApi
import com.example.bawagcodechalenge.domain.model.Activity
import com.example.bawagcodechalenge.domain.repository.ActivityRepository
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val api: BoredApi
) : ActivityRepository {
    override suspend fun getActivity(): Activity {
        return api.getRandomActivity()
    }

    override suspend fun filterActivity(type: String): List<Activity> {
        return api.getFilteredActivity(type)
    }

}