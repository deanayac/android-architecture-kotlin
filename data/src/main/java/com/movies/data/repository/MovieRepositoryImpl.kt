package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.data.source.LocalDataSource
import com.movies.data.source.RemoteDataSource
import com.movies.domain.InputSearch
import com.movies.domain.MovieImages
import com.movies.domain.PopularMovie

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun popularMovies(): Resource<List<PopularMovie>> {
        return remoteDataSource.listPopularMovies()
    }

    override suspend fun searchMovies(description: String): Resource<List<PopularMovie>> {
        localDataSource.insertInputSearch(InputSearch(description))
        return remoteDataSource.searchMovies(description)
    }

    override suspend fun getMovieImage(id: Int): Resource<MovieImages> {
        return remoteDataSource.getMovieImage(id)
    }
}