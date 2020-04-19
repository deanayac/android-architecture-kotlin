package com.bootcamp.kotlin.ui.search

import com.bootcamp.kotlin.data.repositories.MoviesRepository
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.data.server.Movie
import com.movies.data.common.Status
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