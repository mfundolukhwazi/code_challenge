package com.example.bawagcodechalenge.data.remote

import com.example.bawagcodechalenge.domain.model.Activity
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredApi {

   @GET("random")
   suspend fun getRandomActivity(): Activity
   @GET("filter")
   suspend fun getFilteredActivity(@Query("type") type:String): List<Activity>

}