package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.networking.Resource
interface MoviesRepository {
    suspend fun popularMovies(): Resource<List<Movie>>
    suspend fun searchMovies(description:String): Resource<List<Movie>>
}
