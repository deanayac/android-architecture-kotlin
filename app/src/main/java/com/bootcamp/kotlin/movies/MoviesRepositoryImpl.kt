package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.networking.Resource
import com.bootcamp.kotlin.networking.ResponseHandler
import com.bootcamp.kotlin.networking.ResultType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun popularMovies(): Resource<List<Movie>> {
        val api = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(Movies::class.java) }

        return try {
            val movies = api.popularMovies(
                apiKey = "5de961ca47ac20a3689205becc3c3b20",
                page = "1",
                language = "en-US"
            )
            ResponseHandler().handleSuccess(movies.results)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}