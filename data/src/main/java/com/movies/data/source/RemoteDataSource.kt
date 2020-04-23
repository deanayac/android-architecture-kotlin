package com.movies.data.source

import com.movies.data.common.Resource
import com.movies.domain.FavoriteMovie
import com.movies.domain.Movie
import com.movies.domain.MovieImages

interface RemoteDataSource {
    suspend fun listPopularMovies(): Resource<List<Movie>>
    suspend fun searchMovies(description:String):Resource<List<Movie>>
    suspend fun getMovieImage(id: Int) : Resource<MovieImages>
    suspend fun listFavoriteMovies(): Resource<List<FavoriteMovie>>
    suspend fun movieDetail(movieId: Int): Resource<Movie>
}