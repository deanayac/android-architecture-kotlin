package com.movies.interactor

import com.movies.data.repository.MovieRepository
import com.movies.domain.Movie

class GetPopularMovies(private val movieRepository: MovieRepository) {
    suspend fun invoke(): List<Movie> = movieRepository.popularMovies()
}