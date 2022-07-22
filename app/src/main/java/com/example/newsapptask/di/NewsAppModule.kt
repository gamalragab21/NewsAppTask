package com.example.newsapptask.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsapptask.common.NewsDrawables
import com.example.newsapptask.data.remote.NewsServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsAppModule {


    @Singleton
    @Provides
    fun provideFurnitureServiceApi(
        retrofit: Retrofit
    ): NewsServiceApi {
        return retrofit.create(NewsServiceApi::class.java)
    }








    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ) = context
    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context,
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(NewsDrawables.ic_image)
            .error(NewsDrawables.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA))


}