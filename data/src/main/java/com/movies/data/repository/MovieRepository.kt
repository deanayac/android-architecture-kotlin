package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.domain.PopularMovie

interface MovieRepository {
    suspend fun popularMovies(): Resource<List<PopularMovie>>
}