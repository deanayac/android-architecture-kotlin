package com.bootcamp.kotlin.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.kotlin.ui.common.Scope
import com.movies.domain.Movie
import com.movies.interactor.GetPopularMovies
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getPopularMovies: GetPopularMovies
) : ViewModel(), Scope by Scope.Impl() {

    init {
        createScope()
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val movies: List<Movie>) : UiModel()
        class Navigation(val movie: Movie) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) {
                loadMovies()
            }

            return _model
        }

    private fun loadMovies() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getPopularMovies.invoke())
        }
    }

    fun onMovieClicked(movie: Movie) {
        _model.value = UiModel.Navigation(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}