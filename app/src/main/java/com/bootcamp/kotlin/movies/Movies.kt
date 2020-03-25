package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Movies {
    @GET("3/movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MoviesBase

    @GET("3/account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: String?,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): FavoriteMoviesResponse
}