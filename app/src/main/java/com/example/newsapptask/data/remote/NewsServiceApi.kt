package com.example.newsapptask.data.remote

import com.example.newsapptask.common.Constants
import com.example.newsapptask.data.models.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET("v3/content/nyt/world.json")
    suspend fun getNewsResult(@Query("api-key") apiKey:String=Constants.API_KEY):NewsDto
}