package com.example.newsapptask.domain.repositories

import com.example.newsapptask.common.Constants
import com.example.newsapptask.data.models.NewsDto
import retrofit2.http.Query

interface IMainRepository {

    suspend fun getNewsResult(): NewsDto

}