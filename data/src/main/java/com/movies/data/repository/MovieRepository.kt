package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.domain.Movie
import com.movies.domain.PopularMovie

interface MovieRepository {
    suspend fun popularMovies(): Resource<List<PopularMovie>>
    suspend fun searchMovies(description:String): Resource<List<PopularMovie>>

}