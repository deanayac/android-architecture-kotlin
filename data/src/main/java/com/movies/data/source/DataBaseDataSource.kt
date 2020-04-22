package com.movies.data.source

import com.movies.domain.InputSearch
import com.movies.domain.Movie

interface DataBaseDataSource {
    suspend fun popularMovies(): List<Movie>
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun insertInputSearch(inputSearch: InputSearch)
    suspend fun getAllInputSearch():List<InputSearch>?
}