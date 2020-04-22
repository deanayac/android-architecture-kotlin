package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieRepository

import com.movies.domain.Movie

class GetSearchMovies(private val movieRepository: MovieRepository) {
    suspend fun invoke(description:String): Resource<List<Movie>> = movieRepository.searchMovies(description)
}