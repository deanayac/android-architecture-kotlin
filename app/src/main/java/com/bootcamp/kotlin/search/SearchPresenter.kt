package com.bootcamp.kotlin.search

import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.database.InputSearch
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.MoviesRepository
import com.bootcamp.kotlin.networking.Status
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchPresenter(private val view: SearchContract.View,
                      private val repository:MoviesRepository,
                      private val searchRepository: InputSearchRepository) :
    SearchContract.Presenter,Scope by Scope.Impl(){

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
                when(movies.status){
                    Status.SUCCESS -> {
                        view.showMovies(filterMovies(movies.data?: fail(ERROR_MOVIES)))
                        registerInput(description)
                    }
                    Status.ERROR -> Timber.e(movies.message)
                }
            view.showProgress(isVisible = false)
        }
    }

    override  fun getInputs() {
        launch {
            var inputs = searchRepository.getAllInputSearch()
            if(inputs.isNotEmpty()){
              var inputs =  inputs.map { it.description }
                view.showInputs(inputs)
            }
            Timber.d("input = $inputs.toString()")
        }
    }

    private fun filterMovies(movies:List<Movie>)= movies.filterNot{ it.backdropPath.isNullOrEmpty()}

    override  fun registerInput( input: String){
        launch {
            searchRepository.insertInputSearch(InputSearch(0, input))
        }
    }

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    override fun onDestroyScope() {
        destroyScope()
    }
}