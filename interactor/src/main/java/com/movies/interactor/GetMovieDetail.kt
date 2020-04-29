package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieRepository
import com.movies.domain.Movie

class GetMovieDetail(private val movieRepository: MovieRepository) {
    suspend fun invoke(movieId: Int): Resource<Movie> {
        return movieRepository.movieDetail(movieId)
    }
}