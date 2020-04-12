package com.bootcamp.kotlin.search

import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.MoviesRepository
import com.bootcamp.kotlin.networking.Status
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchPresenter(
    private val view: SearchContract.View,
    private val repository: MoviesRepository
) : SearchContract.Presenter, Scope by Scope.Impl() {

    companion object {
        private const val ERROR_MOVIES = "Fail List Movies"
    }

    override fun onCreateScope() {
        createScope()
    }

    override fun searchMovies(description: String) {
        launch {
            view.showProgress(isVisible = true)

            val movies = repository.searchMovies(description)
            when (movies.status) {
                Status.SUCCESS -> view.showMovies(filterMovies(movies.data ?: fail(ERROR_MOVIES)))
                Status.ERROR -> Timber.d("error %s", movies.message)
            }
            view.showProgress(isVisible = false)
        }
    }

    private fun filterMovies(movies: List<Movie>) =
        movies.filterNot { it.backdropPath.isNullOrEmpty() }

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    override fun onDestroyScope() {
        destroyScope()
    }
}