package com.bootcamp.kotlin.data.server

import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse
import com.bootcamp.kotlin.movies.MoviesBase
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbServices {

    @GET("3/movie/{movie_id}")
    suspend fun movieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieDbResult

    @GET("3/movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MoviesBase

    @GET("3/account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int?,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): FavoriteMoviesResponse

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): MoviesBase

    @GET("3/movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieImages
}