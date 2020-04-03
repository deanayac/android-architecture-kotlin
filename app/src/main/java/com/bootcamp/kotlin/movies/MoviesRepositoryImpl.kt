package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.networking.Resource
import com.bootcamp.kotlin.networking.ResponseHandler
import retrofit2.Retrofit

class MoviesRepositoryImpl(private val retrofit: Retrofit, private val apiKey: String) : MoviesRepository {

    companion object {
        const val DEFAULT_LANGUAGE = "en-US"
        const val DEFAULT_PAGE = "1"
    }

    override suspend fun popularMovies(): Resource<List<Movie>> {

        return try {
            val api = retrofit.run { create(Movies::class.java) }
            val movies = api.popularMovies(
                apiKey = apiKey,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE
            )

            ResponseHandler().handleSuccess(movies.results)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}