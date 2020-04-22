package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.domain.FavoriteMovie
import com.movies.domain.Movie
import com.movies.domain.MovieImages

interface MovieRepository {
    suspend fun popularMovies(): List<Movie>
    suspend fun searchMovies(description:String): Resource<List<Movie>>
    suspend fun getMovieImage(id: Int) : Resource<MovieImages>
    suspend fun favoriteMovies(): Resource<List<FavoriteMovie>>
    suspend fun movieDetail(movieId: Int): Resource<Movie>
}