package com.bootcamp.kotlin.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.kotlin.ui.common.Event
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.ui.common.ScopeViewModel
import com.movies.domain.Movie
import com.movies.interactor.GetPopularMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MoviesViewModel(
    uiDispatcher: CoroutineDispatcher,
    private val getPopularMovies: GetPopularMovies
) : ScopeViewModel(uiDispatcher) {

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

    private val _navigation = MutableLiveData<Event<Movie>>()
    val navigation: LiveData<Event<Movie>> = _navigation

    private fun loadMovies() {
        launch {
            _model.value = MovieUiModel.Loading
            _model.value = MovieUiModel.Content(getPopularMovies.invoke())
        }
    }

    fun onMovieClicked(movie: Movie) {
        _navigation.value = Event(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}