package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.movies.MoviesBase
import retrofit2.http.GET
import retrofit2.http.Query

interface Movies{
    @GET("3/movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MoviesBase

}