package com.bootcamp.kotlin.search

import android.util.Log
import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.database.InputSearch
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.MoviesRepository
import com.bootcamp.kotlin.networking.Status
import kotlinx.coroutines.launch

class SearchPresenter(private val view: SearchContract.View,
                      private val repository:MoviesRepository,
                      private val searchRepository: InputSearchRepository) :
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
                    Status.SUCCESS -> {
                        view.showMovies(filterMovies(movies.data?: fail(ERROR_MOVIES)))
                        registerInput(description)
                    }
                    Status.ERROR -> Log.d("error",movies.message)
                }
            view.showProgress(isVisible = false)
        }
    }

    override suspend  fun getInputs() {
        var inputs = searchRepository.getAllInputSearch()
        Log.d("Inpus", inputs.toString())    }

    private fun filterMovies(movies:List<Movie>)= movies.filterNot{ it.backdropPath.isNullOrEmpty()}

    suspend fun registerInput( input: String){
        searchRepository.insertInputSearch(InputSearch(0,input))
    }

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
}
