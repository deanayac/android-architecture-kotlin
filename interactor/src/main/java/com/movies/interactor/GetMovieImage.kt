package com.movies.interactor

import com.movies.data.common.Resource
import com.movies.data.repository.MovieImageDetailRepository
import com.movies.domain.MovieImages

/**
 * Created by jhon on 20/04/2020
 */
class GetMovieImage(private val movieImageDetailRepository: MovieImageDetailRepository) {
    suspend fun invoke(id: Int) : Resource<MovieImages> = movieImageDetailRepository.getMovieImageDetail(id)
}