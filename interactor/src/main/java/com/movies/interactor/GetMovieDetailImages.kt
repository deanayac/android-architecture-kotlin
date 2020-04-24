package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieRepository
import com.movies.domain.Movie
import com.movies.domain.MovieImages

class GetMovieDetailImages(private val movieRepository: MovieRepository) {
    suspend fun invoke(movieId: Int): Resource<MovieImages> {
        return movieRepository.getMovieImages(movieId)
    }
}