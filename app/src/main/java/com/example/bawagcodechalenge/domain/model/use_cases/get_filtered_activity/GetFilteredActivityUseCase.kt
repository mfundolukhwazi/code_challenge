package com.example.bawagcodechalenge.domain.model.use_cases.get_filtered_activity

import com.example.bawagcodechalenge.common.Resource
import com.example.bawagcodechalenge.domain.model.Activity
import com.example.bawagcodechalenge.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetFilteredActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(type: String): Flow<Resource<List<Activity>>> = flow {
        try {
            emit(Resource.Loading<List<Activity>>());
            val activities = repository.filterActivity(type)
            emit(Resource.Success<List<Activity>>(activities))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Activity>>(message = e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach server. Check your connection"))
        }
    }
}