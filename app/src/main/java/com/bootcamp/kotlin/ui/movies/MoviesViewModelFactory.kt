package com.bootcamp.kotlin.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.interactor.GetPopularMovies

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val getPopularMovies: GetPopularMovies) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(getPopularMovies) as T
    }
}