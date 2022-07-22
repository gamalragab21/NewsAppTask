package com.example.newsapptask.domain.repositories

import com.example.newsapptask.data.models.NewsDto

interface IMainRepository {

    suspend fun getNewsResult(): NewsDto

}