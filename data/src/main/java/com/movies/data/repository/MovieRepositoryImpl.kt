package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.data.source.DataBaseDataSource
import com.movies.data.source.RemoteDataSource
import com.movies.domain.FavoriteMovie
import com.movies.domain.InputSearch
import com.movies.domain.Movie
import com.movies.domain.MovieImages

class MovieRepositoryImpl(
    private val dataBaseDataSource: DataBaseDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun popularMovies(): List<Movie> {
        if (dataBaseDataSource.moviesCount() == 0) {
            val movies = remoteDataSource.listPopularMovies()
            movies.data?.let {
                dataBaseDataSource.saveMovies(it)
            }
        }

        return dataBaseDataSource.popularMovies()
    }

    override suspend fun searchMovies(description: String): Resource<List<Movie>> {
        dataBaseDataSource.insertInputSearch(InputSearch(description))
        return remoteDataSource.searchMovies(description)
    }

    override suspend fun movieDetail(movieId: Int): Resource<Movie> {
        return remoteDataSource.movieDetail(movieId)
    }

    override suspend fun getMovieImage(id: Int): Resource<MovieImages> {
        return remoteDataSource.getMovieImage(id)
    }

    override suspend fun favoriteMovies(): Resource<List<FavoriteMovie>> {
        return remoteDataSource.listFavoriteMovies()
    }
}