package com.bootcamp.kotlin.ui.movies

import com.bootcamp.kotlin.ui.common.Scope
import com.movies.interactor.GetPopularMovies
import kotlinx.coroutines.launch

class MoviesPresenter(
    private val view: MoviesContract.View,
    private val getPopularMovies: GetPopularMovies
) : MoviesContract.Presenter, Scope by Scope.Impl() {

    private fun loadMovies() {
        launch {
            view.showProgress(isVisible = true)
            val movies = getPopularMovies.invoke()
            view.showMovies(movies)
            view.showProgress(isVisible = false)
        }
    }

    override fun onCreateScope() {
        createScope()
        loadMovies()
    }

    override fun onDestroyScope() {
        destroyScope()
    }

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
}