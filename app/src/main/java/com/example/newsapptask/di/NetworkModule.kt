package com.example.newsapptask.di

import com.example.newsapptask.data.cache.ComplexPreferences
import com.example.newsapptask.common.Constants.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
//            .addInterceptor { chain ->
//                val original: Request = chain.request()
//                val builder: Request.Builder =original.newBuilder()
//                val userData=complexPreferences.getObject(Constants.USER_DATA, User::class.java)
//                val lang=complexPreferences.getString(Constants.USER_LANG,"ar")
//
//                val newRequest = builder.apply {
//                    addHeader("Accept-Language", lang)
//                    userData?.also {
//                        addHeader("Accept", "application/json")
//                        addHeader("Authorization", "Bearer ${it.token}")
//                        build()
//                    }
//                }
//                return@addInterceptor chain.proceed(newRequest.build())
//            }
            .build()

    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

}