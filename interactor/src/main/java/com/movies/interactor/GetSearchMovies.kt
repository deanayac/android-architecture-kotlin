package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieRepository
import com.movies.domain.PopularMovie

class GetSearchMovies(private val movieRepository: MovieRepository) {
    suspend fun invoke(description:String): Resource<List<PopularMovie>> = movieRepository.searchMovies(description)
}