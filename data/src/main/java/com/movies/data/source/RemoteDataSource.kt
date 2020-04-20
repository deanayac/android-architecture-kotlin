package com.movies.data.source

import com.movies.data.common.Resource
import com.movies.domain.MovieImages
import com.movies.domain.PopularMovie

interface RemoteDataSource {
    suspend fun listPopularMovies(): Resource<List<PopularMovie>>
    suspend fun searchMovies(description:String):Resource<List<PopularMovie>>
    suspend fun getMovieImage(id: Int) : Resource<MovieImages>
}