package com.bootcamp.kotlin.ui.search

import com.bootcamp.kotlin.ui.common.Scope
import com.movies.data.common.Status
import com.movies.domain.Movie
import com.movies.interactor.GetSearchAutocomplete
import com.movies.interactor.GetSearchMovies
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchPresenter(
    private val view: SearchContract.View,
    private val getSearchAutocomplete: GetSearchAutocomplete,
    private val getSearchMovies: GetSearchMovies
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

            val movies = getSearchMovies.invoke(description)
            when (movies.status) {
                Status.SUCCESS -> {
                    view.showMovies(filterMovies(movies.data ?: fail(ERROR_MOVIES)))
                }
                Status.ERROR -> Timber.e(movies.message)
            }
            view.showProgress(isVisible = false)
        }
    }

    override fun getInputs() {
        launch {
            var inputs = getSearchAutocomplete.invoke()
            if (inputs != null) {
                if (inputs.isNotEmpty()) {
                    var inputs = inputs.map { it.description }
                    view.showInputs(inputs)
                }
            }
            Timber.d("input = $inputs.toString()")
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