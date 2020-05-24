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

    private val _model = MutableLiveData<MovieUiModel>()
    val model: LiveData<MovieUiModel>
        get() {
            if (_model.value == null) {
                loadMovies()
            }

            return _model
        }

    private fun loadMovies() {
        launch {
            _model.value = MovieUiModel.Loading
            _model.value = MovieUiModel.Content(getPopularMovies.invoke())
        }
    }

    fun onMovieClicked(movie: Movie) {
        _model.value = MovieUiModel.Navigation(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}