package com.bootcamp.kotlin.movies

import android.util.Log
import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.networking.Status
import kotlinx.coroutines.launch

class MoviesPresenter(private val view: MoviesContract.View,
                      private val repository: MoviesRepository) :
    MoviesContract.Presenter, Scope by Scope.Impl(){

    private fun loadMovies() {
        launch {
            view.showProgress(isVisible = true)
            val movies = repository.popularMovies()
                when (movies.status) {
                    Status.SUCCESS -> view.showMovies(movies.data?: fail("Fail List Movies "))
                    Status.ERROR -> Log.d("error",movies.message)
                }
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

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
}
