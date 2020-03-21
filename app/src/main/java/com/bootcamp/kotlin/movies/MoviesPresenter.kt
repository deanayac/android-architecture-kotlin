package com.bootcamp.kotlin.movies

import com.bootcamp.kotlin.common.Scope
import kotlinx.coroutines.launch

class MoviesPresenter(private val view: MoviesContract.View,
                      private val repository: MoviesRepository) :
    MoviesContract.Presenter, Scope by Scope.Impl(){

    private fun loadMovies() {
        launch {
            view.showProgress(isVisible = true)
            val movies = repository.popularMovies()
            view.showMovies(movies)
            view.showProgress(isVisible = false)
        }
    }

    override fun onCreate() {
        initScope()
        loadMovies()
    }

    override fun onDestroy() {
        destroyScope()
    }
}