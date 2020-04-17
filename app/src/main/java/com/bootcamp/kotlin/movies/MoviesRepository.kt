package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.domain.MovieImages
import  com.bootcamp.kotlin.domain.Movie as DomainMovie
import com.bootcamp.kotlin.networking.Resource

interface MoviesRepository {
    suspend fun popularMovies(): Resource<List<Movie>>
    suspend fun searchMovies(description:String): Resource<List<Movie>>
    suspend fun movie(id: Int): Resource<DomainMovie>
    suspend fun movieImages(id: Int): Resource<MovieImages>
}