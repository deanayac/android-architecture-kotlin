package com.bootcamp.kotlin.data.source

import com.bootcamp.kotlin.data.server.MovieDbServices

import com.bootcamp.kotlin.data.ResponseHandler
import com.bootcamp.kotlin.data.toDomainFavoriteMovie
import com.bootcamp.kotlin.data.toDomainMovie
import com.bootcamp.kotlin.data.toDomainMovieImages
import com.movies.data.common.Resource
import com.movies.data.source.RemoteDataSource
import com.movies.domain.FavoriteMovie
import com.movies.domain.Movie
import com.movies.domain.MovieImages

class RetrofitDataSource(
    private val movieDbServices: MovieDbServices,
    private val apiKey: String
) : RemoteDataSource {

    companion object {
        private const val DEFAULT_LANGUAGE = "en-US"
        private const val DEFAULT_PAGE = "1"
        private const val API_KEY_2 = "d9ae4921794c06bd0fdbd1463d274804"
        private const val sessionId = "878539e32c923af4c422e5c0b1fa015ba4aa2dfe"
    }

    override suspend fun listPopularMovies(): Resource<List<Movie>> {
        return try {
            val movies = movieDbServices.popularMovies(
                apiKey = apiKey,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE
            ).run {
                results.map {
                    it.toDomainMovie()
                }
            }

            ResponseHandler().handleSuccess(movies)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun movieDetail(movieId: Int): Resource<Movie> {
        return try {
            val movie = movieDbServices.movieDetail(
                id = movieId,
                apiKey = apiKey,
                language = DEFAULT_LANGUAGE
            ).run {
                this.toDomainMovie()
            }

            ResponseHandler().handleSuccess(movie)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun listFavoriteMovies(): Resource<List<FavoriteMovie>> {
        return try {
            val favoriteMovies = movieDbServices.getFavoriteMovies(
                accountId = 1,
                apiKey = API_KEY_2,
                sessionId = sessionId
            ).run {
                results.map {
                    it.toDomainFavoriteMovie()
                }
            }

            ResponseHandler().handleSuccess(favoriteMovies)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }


    override suspend fun searchMovies(description: String): Resource<List<Movie>> {
        return try {
            val movies = movieDbServices.searchMovies(
                apiKey = apiKey,
                page = DEFAULT_PAGE,
                language = DEFAULT_LANGUAGE,
                query = description
            ).run {
                results.map {
                    it.toDomainMovie()
                }
            }

            ResponseHandler().handleSuccess(movies)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun getMovieImage(id: Int): Resource<MovieImages> {
        return try {
            val movieImage = movieDbServices.getMovieImages(
                id = id,
                apiKey = apiKey
            )
            ResponseHandler().handleSuccess(movieImage.toDomainMovieImages())
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}