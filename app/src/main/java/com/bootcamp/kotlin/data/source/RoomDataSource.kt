package com.bootcamp.kotlin.data.source

import com.bootcamp.kotlin.data.database.AppDatabase
import com.bootcamp.kotlin.data.provider.ApplicationProvider
import com.bootcamp.kotlin.data.toDataBaseMovie
import com.bootcamp.kotlin.data.toDomainEntityIn
import com.bootcamp.kotlin.data.toDomainMovie
import com.bootcamp.kotlin.data.toDomainToEntity
import com.movies.data.source.DataBaseDataSource
import com.movies.domain.InputSearch
import com.movies.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource : DataBaseDataSource {
    override suspend fun popularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        var movies = listOf<Movie>()
        ApplicationProvider.listen { application ->
            val database = AppDatabase.getInstance(application)
            movies = database.movieDao().getAll().map {
                it.toDomainMovie()
            }
        }

        movies
    }

    override suspend fun moviesCount(): Int = withContext(Dispatchers.IO) {
        var moviesCount = 0

        ApplicationProvider.listen { application ->
            val database = AppDatabase.getInstance(application)
            moviesCount = database.movieDao().movieCount()
        }

        moviesCount
    }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        ApplicationProvider.listen { application ->
            val database = AppDatabase.getInstance(application)
            val movieList = movies.map {
                it.toDataBaseMovie()
            }

            database.movieDao().insertMovies(movieList)
        }
    }

    override suspend fun insertInputSearch(inputSearch: InputSearch) = withContext(Dispatchers.IO) {
        ApplicationProvider.listen { application ->
            val database = AppDatabase.getInstance(application)
            database.searchDao().insertInputSearch(inputSearch.toDomainToEntity())
        }
    }

    override suspend fun getAllInputSearch(): List<InputSearch>? = withContext(Dispatchers.IO) {
        var listInput: List<InputSearch>? = null
        ApplicationProvider.listen { application ->
            val database = AppDatabase.getInstance(application)
            listInput = database.searchDao().getAll().map {
                it.toDomainEntityIn()
            }
        }
        listInput
    }
}