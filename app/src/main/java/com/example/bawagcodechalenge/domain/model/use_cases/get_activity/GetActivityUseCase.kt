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
            emit(Resource.Loading<Activity>());
            val activity = repository.getActivity();
            emit(Resource.Success<Activity>(activity))
        }catch (e: HttpException)
        {
            emit(Resource.Error<Activity>(message = e.localizedMessage))
        }catch (e: IOException)
        {
            emit(Resource.Error<Activity>(message = "Could not reach server. Check your connection"))
        }
    }
}