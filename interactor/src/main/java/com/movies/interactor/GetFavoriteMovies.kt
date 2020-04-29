package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieRepository
import com.movies.domain.FavoriteMovie

class GetFavoriteMovies(private val movieRepository: MovieRepository) {
    suspend fun invoke(): Resource<List<FavoriteMovie>> = movieRepository.favoriteMovies()
}