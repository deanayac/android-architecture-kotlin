package com.bootcamp.kotlin.ui.movies

import android.util.Log
import com.bootcamp.kotlin.ui.common.Scope
import com.movies.data.common.Status
import com.movies.interactor.GetPopularMovies
import kotlinx.coroutines.launch

class MoviesPresenter(private val view: MoviesContract.View,
                      private val getPopularMovies: GetPopularMovies
) :
    MoviesContract.Presenter, Scope by Scope.Impl(){

    private fun loadMovies() {
        launch {
            view.showProgress(isVisible = true)
            val movies = getPopularMovies.invoke()
                /*when (movies.status) {
                    Status.SUCCESS -> view.showMovies(movies.data?: fail("Fail List Movies "))
                    Status.ERROR -> Log.d("error",movies.message)
                }*/
            view.showMovies(movies)
            view.showProgress(isVisible = false)
                }
    }

    override fun onCreateScope () {
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
