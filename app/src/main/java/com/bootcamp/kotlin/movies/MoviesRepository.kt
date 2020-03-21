package com.bootcamp.kotlin.movies

interface MoviesRepository {
    suspend fun popularMovies(): List<Movie>
}