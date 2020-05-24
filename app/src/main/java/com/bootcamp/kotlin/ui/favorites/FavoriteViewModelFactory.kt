package com.bootcamp.kotlin.ui.favorites

import FavoriteViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.interactor.GetFavoriteMovies

@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory(private val getFavoriteMovies: GetFavoriteMovies) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(getFavoriteMovies) as T
    }
}