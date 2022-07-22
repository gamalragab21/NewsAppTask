package com.example.newsapptask.data.repositories

import com.example.newsapptask.data.models.NewsDto
import com.example.newsapptask.data.remote.NewsServiceApi
import com.example.newsapptask.domain.repositories.IMainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val apiService: NewsServiceApi
) : IMainRepository {
    override suspend fun getNewsResult(): NewsDto =apiService.getNewsResult()
}