package com.bootcamp.kotlin.data.server

import com.bootcamp.kotlin.BuildConfig
import com.bootcamp.kotlin.ui.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private fun addInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    val okHttpClient = OkHttpClient.Builder().addInterceptor(addInterceptor())
        .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
        .readTimeout(Constants.readTimeOut, TimeUnit.SECONDS)
        .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieDbServices: MovieDbServices by lazy { retrofit().create(MovieDbServices::class.java) }
}