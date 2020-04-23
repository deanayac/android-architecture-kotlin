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

    fun buildService(): Retrofit {
        val httpClient = OkHttpClient.Builder().addInterceptor(addInterceptor())
            .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
            .readTimeout(Constants.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}