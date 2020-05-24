package com.bootcamp.kotlin.ui.favorites

import com.movies.domain.FavoriteMovie

sealed class FavoriteUiModel {
    object Loading : FavoriteUiModel()
    class Content(val favoriteMovies: List<FavoriteMovie>) : FavoriteUiModel()
}