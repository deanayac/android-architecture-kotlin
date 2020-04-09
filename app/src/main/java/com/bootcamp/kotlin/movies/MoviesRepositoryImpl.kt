package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.networking.Resource
import com.bootcamp.kotlin.networking.ResponseHandler
import retrofit2.HttpException
import retrofit2.Retrofit

class MoviesRepositoryImpl(private val retrofit: Retrofit) : MoviesRepository {

    companion object {
        const val DEFAULT_LANGUAGE = "en-US"
        const val DEFAULT_PAGE = "1"
        const val API_KEY = "5de961ca47ac20a3689205becc3c3b20"
    }

    override suspend fun popularMovies(): Resource<List<Movie>> {
        return try {
            val api = retrofit.run { create(Movies::class.java) }
            val movies = api.popularMovies(
                apiKey = API_KEY,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE
            )
            ResponseHandler().handleSuccess(movies.results)
        } catch (e: HttpException) {
            ResponseHandler().handleException(e)
        } catch (e : IllegalArgumentException){
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun searchMovies(description: String): Resource<List<Movie>> {
        return try {
            val api = retrofit.run { create(Movies::class.java) }
            val movies = api.searchMovies(
                apiKey = API_KEY,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE,
                query = description
            )
            ResponseHandler().handleSuccess(movies.results)
        } catch (e: HttpException) {
            ResponseHandler().handleException(e)
        } catch (e: IllegalArgumentException) {
            ResponseHandler().handleException(e)
        }
    }}
