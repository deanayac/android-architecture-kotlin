package com.bootcamp.kotlin.data.repositories

import com.bootcamp.kotlin.data.server.Movie
import com.bootcamp.kotlin.data.server.MovieImages
import com.movies.domain.Movie as DomainMovie
import com.movies.data.common.Resource

interface MoviesRepository {
    suspend fun searchMovies(description: String): Resource<List<Movie>>
    suspend fun movie(id: Int): Resource<DomainMovie>
}