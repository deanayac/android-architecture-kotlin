package com.bootcamp.kotlin.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bootcamp.kotlin.ui.common.ScopedViewModel
import com.movies.data.common.Status
import com.movies.domain.Movie
import com.movies.interactor.GetSearchAutocomplete
import com.movies.interactor.GetSearchMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(
    private val getSearchAutocomplete:GetSearchAutocomplete,
    private val getSearchMovies: GetSearchMovies,
    override val uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher){

    sealed class UiModel {
        object Loading : UiModel()
        object showError : UiModel()
        data class SearchMovie(val movies: List<Movie>) : UiModel()
        data class Autocomplete(val inputs: List<String>) : UiModel()
    }

    companion object {
        private const val ERROR_MOVIES = "Fail List Movies"
    }

    private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private fun refresh(){
        _model.value = UiModel.Autocomplete(emptyList())
    }

     fun searchMovies(description: String){
        launch {
            _model.value = UiModel.Loading
            val movies = getSearchMovies.invoke(description)
            when(movies.status){
                Status.SUCCESS -> {
                    _model.value = UiModel.SearchMovie(filterMovies(movies.data ?: fail(ERROR_MOVIES)))
                }
                Status.ERROR -> {
                    _model.value = UiModel.showError
                    Timber.e(movies.message)
                }
            }
        }
    }

     fun getInputs(){
        launch {
            _model.value = getSearchAutocomplete.invoke()?.let {
                UiModel.Autocomplete(it.map { v -> v.description })
            }
        }
    }

    private fun filterMovies(movies: List<Movie>) =
        movies.filterNot { it.backdropPath.isNullOrEmpty() }

    private fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
}