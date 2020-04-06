package com.bootcamp.kotlin.search

import android.util.Log
import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.movies.MoviesRepository
import com.bootcamp.kotlin.networking.Status
import kotlinx.coroutines.launch

class SearchPresenter(private val view: SearchContract.View,
                      private val repository:MoviesRepository) :
    SearchContract.Presenter,Scope by Scope.Impl(){

    companion object{
        const val ERROR_MOVIES = "Fail List Movies"
    }

    override fun initView() {
        initScope()
    }

    override fun searchMovies(description:String) {
        launch {
            view.showProgress(isVisible = true)

            val movies = repository.searchMovies(description)
                when(movies.status){
                    Status.SUCCESS -> view.showMovies(movies.data?: fail(ERROR_MOVIES))
                    Status.ERROR -> Log.d("error",movies.message)
                }
            view.showProgress(isVisible = false)
        }
    }

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
}
