package com.bootcamp.kotlin.data.source

import com.bootcamp.kotlin.data.server.MovieDbServices
import com.bootcamp.kotlin.data.toDomainPopularMovie
import com.bootcamp.kotlin.networking.ResponseHandler
import com.movies.data.common.Resource
import com.movies.data.source.RemoteDataSource
import com.movies.domain.PopularMovie
import retrofit2.Retrofit

class RetrofitDataSource(private val retrofit: Retrofit): RemoteDataSource {

    companion object {
        private const val DEFAULT_LANGUAGE = "en-US"
        private const val DEFAULT_PAGE = "1"
        private const val API_KEY = "5de961ca47ac20a3689205becc3c3b20"
    }

    override suspend fun listPopularMovies(): Resource<List<PopularMovie>> {
        return try {
            val api = retrofit.run { create(MovieDbServices::class.java) }
            val movies = api.popularMovies(
                apiKey = API_KEY,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE
            )

            ResponseHandler().handleSuccess(movies.results.toDomainPopularMovie())
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun searchMovies(description: String): Resource<List<PopularMovie>> {
        return try {
            val api = retrofit.run { create(MovieDbServices::class.java) }
            val movies = api.searchMovies(
                apiKey = API_KEY,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE,
                query = description
            )

            ResponseHandler().handleSuccess(movies.results.toDomainPopularMovie())
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}