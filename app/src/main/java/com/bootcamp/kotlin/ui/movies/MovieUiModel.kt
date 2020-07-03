package com.bootcamp.kotlin.ui.movies

import com.movies.domain.Movie

sealed class MovieUiModel {
    object Loading : MovieUiModel()
    data class Content(val movies: List<Movie>) : MovieUiModel()
}