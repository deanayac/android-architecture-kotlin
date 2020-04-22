package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.data.source.RemoteDataSource
import com.movies.domain.MovieImages

/**
 * Created by jhon on 20/04/2020
 */
class MovieImageDetailRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MovieImageDetailRepository {

    override suspend fun getMovieImageDetail(id: Int): Resource<MovieImages> {
        return remoteDataSource.getMovieImage(id)
    }
}