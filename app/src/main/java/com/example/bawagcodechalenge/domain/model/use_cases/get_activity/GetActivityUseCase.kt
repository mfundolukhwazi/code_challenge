package com.example.bawagcodechalenge.domain.model.use_cases.get_activity

import com.example.bawagcodechalenge.common.Resource
import com.example.bawagcodechalenge.domain.model.Activity
import com.example.bawagcodechalenge.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(): Flow<Resource<Activity>> = flow {
        try {
            emit(Resource.Loading());
            val activity = repository.getActivity();
            emit(Resource.Success(activity))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach server. Check your connection"))
        } catch (e: RuntimeException) {
            emit(Resource.Error(message = "An error occurred"))
        }
    }
}