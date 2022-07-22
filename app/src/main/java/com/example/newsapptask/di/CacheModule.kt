package com.example.newsapptask.di

import android.content.Context
import com.example.newsapptask.data.cache.ComplexPreferences
import com.example.newsapptask.common.Constants.MODE_PRIVATE
import com.example.newsapptask.common.Constants.PREF_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideComplexPreference(@ApplicationContext context: Context,): ComplexPreferences =ComplexPreferences(context, PREF_FILE, MODE_PRIVATE)

}