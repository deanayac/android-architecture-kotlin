package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieRepository
import com.movies.domain.PopularMovie

class GetPopularMovies(private val movieRepository: MovieRepository) {
    suspend fun invoke(): Resource<List<PopularMovie>> = movieRepository.popularMovies()
}