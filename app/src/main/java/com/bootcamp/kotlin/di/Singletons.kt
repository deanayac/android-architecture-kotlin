package com.bootcamp.kotlin.di

import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.movies.Movies
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Singletons {

    private fun addInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun buildService(): Movies? {
        val httpClient = OkHttpClient.Builder().addInterceptor(addInterceptor())
            .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
            .readTimeout(Constants.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl("BASE_URL")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Movies::class.java)
    }

}