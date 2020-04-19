package com.movies.data.source

import com.movies.data.common.Resource
import com.movies.domain.PopularMovie

interface RemoteDataSource {
    suspend fun listPopularMovies(): Resource<List<PopularMovie>>
}