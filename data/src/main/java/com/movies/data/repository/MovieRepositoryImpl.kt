package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.data.source.LocalDataSource
import com.movies.data.source.RemoteDataSource
import com.movies.domain.PopularMovie

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun popularMovies(): Resource<List<PopularMovie>> {
        return remoteDataSource.listPopularMovies()
    }
}