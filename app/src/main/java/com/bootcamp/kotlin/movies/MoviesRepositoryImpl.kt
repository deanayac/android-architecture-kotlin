package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.data.server.MovieDbServices
import com.bootcamp.kotlin.data.server.toDomainMovie
import com.bootcamp.kotlin.data.server.toDomainMovieImages
import com.bootcamp.kotlin.domain.MovieImages
import com.bootcamp.kotlin.networking.Resource
import com.bootcamp.kotlin.networking.ResponseHandler
import retrofit2.Retrofit
import com.bootcamp.kotlin.domain.Movie as DomainMovie

class MoviesRepositoryImpl(private val retrofit: Retrofit) : MoviesRepository {

    companion object {
        private const val DEFAULT_LANGUAGE = "en-US"
        private const val DEFAULT_PAGE = "1"
        private const val API_KEY = "5de961ca47ac20a3689205becc3c3b20"
    }

    override suspend fun movie(id: Int): Resource<DomainMovie> {
        return try {
            val api = retrofit.run { create(MovieDbServices::class.java) }
            val movie = api.movieDetail(
                apiKey = API_KEY,
                language = DEFAULT_LANGUAGE,
                id = id
            )
            ResponseHandler().handleSuccess(movie.toDomainMovie())
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun popularMovies(): Resource<List<Movie>> {
        return try {
            val api = retrofit.run { create(MovieDbServices::class.java) }
            val movies = api.popularMovies(
                apiKey = API_KEY,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE
            )
            ResponseHandler().handleSuccess(movies.results)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun searchMovies(description: String): Resource<List<Movie>> {
        return try {
            val api = retrofit.run { create(MovieDbServices::class.java) }
            val movies = api.searchMovies(
                apiKey = API_KEY,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE,
                query = description
            )
            ResponseHandler().handleSuccess(movies.results)
        } catch (e: java.lang.Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun movieImages(id: Int): Resource<MovieImages> {
        return try {
            val api = retrofit.run { create(MovieDbServices::class.java) }
            val movie = api.getMovieImages(
                id = id,
                apiKey = API_KEY
            )
            ResponseHandler().handleSuccess(movie.toDomainMovieImages())
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}