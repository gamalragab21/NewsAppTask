package com.example.newsapptask.di


import com.example.newsapptask.data.remote.NewsServiceApi
import com.example.newsapptask.data.repositories.MainRepositoryImpl
import com.example.newsapptask.domain.repositories.IMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        ioDispatcher: CoroutineDispatcher,
        apiService: NewsServiceApi
    ): IMainRepository = MainRepositoryImpl(ioDispatcher, apiService)
}