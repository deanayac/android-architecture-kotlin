package com.bootcamp.kotlin

import retrofit2.http.GET
import retrofit2.http.Query

interface Authorization {
    @GET("3/authentication/token/new")
    suspend fun requestToken(
        @Query("api_key") apiKey: String
    ): Token
}