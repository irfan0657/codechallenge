package com.example.codechallenge.di

import com.example.codechallenge.data.network.WebApi
import com.example.codechallenge.utils.Constants.Constants.BASE_URL
import com.example.codechallenge.utils.Constants.Constants.timeout
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    /**
     * This function provide logs of request and response
     */
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * This function provides the singleton reference for OkHttpClient
     */
    @Singleton
    @Provides
    fun okHttpInstance(): OkHttpClient {
        val builder = OkHttpClient
            .Builder()
        return builder
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    /**
     * This provides the singleton reference for Retrofit
     */
    @Singleton
    @Provides
    fun retrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    /**
     * This function provides the singleton reference for WebService
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WebApi = retrofit.create(WebApi::class.java)


}