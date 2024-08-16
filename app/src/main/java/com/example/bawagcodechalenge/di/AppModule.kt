package com.example.bawagcodechalenge.di

import androidx.compose.ui.unit.Constraints
import com.example.bawagcodechalenge.common.Constants
import com.example.bawagcodechalenge.data.remote.BoredApi
import com.example.bawagcodechalenge.data.repository.ActivityRepositoryImpl
import com.example.bawagcodechalenge.domain.repository.ActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBoredApi(): BoredApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoredApi::class.java)
    }

    @Provides
    @Singleton
    fun provideActivityRepository(api: BoredApi): ActivityRepository {
        return ActivityRepositoryImpl(api)
    }
}