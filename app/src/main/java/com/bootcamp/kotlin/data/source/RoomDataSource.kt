package com.bootcamp.kotlin.data.source

import com.bootcamp.kotlin.data.database.AppDatabase
import com.bootcamp.kotlin.data.toDataBaseMovie
import com.bootcamp.kotlin.data.toDomainEntityIn
import com.bootcamp.kotlin.data.toDomainMovie
import com.bootcamp.kotlin.data.toDomainToEntity
import com.movies.data.source.DataBaseDataSource
import com.movies.domain.InputSearch
import com.movies.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(private val appDatabase: AppDatabase) : DataBaseDataSource {
    override suspend fun popularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        appDatabase.movieDao().getAll().map {
            it.toDomainMovie()
        }
    }

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        (appDatabase.movieDao().movieCount()) <= 0
    }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        val movieList = movies.map {
            it.toDataBaseMovie()
        }

        appDatabase.movieDao().insertMovies(movieList)
    }

    override suspend fun insertInputSearch(inputSearch: InputSearch) =
        withContext(Dispatchers.IO) {
            appDatabase.searchDao().insertInputSearch(inputSearch.toDomainToEntity())
        }

    override suspend fun getAllInputSearch(): List<InputSearch>? = withContext(Dispatchers.IO) {
        appDatabase.searchDao().getAll().map {
            it.toDomainEntityIn()
        }
    }
}