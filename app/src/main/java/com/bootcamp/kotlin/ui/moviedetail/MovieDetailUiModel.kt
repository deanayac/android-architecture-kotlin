package com.bootcamp.kotlin.ui.moviedetail

import com.movies.domain.Movie
import com.movies.domain.MovieImages

sealed class MovieDetailUiModel {
    object Loading : MovieDetailUiModel()
    class Header(val movie: Movie) : MovieDetailUiModel()
    class Posters(val moviesImages: MovieImages) : MovieDetailUiModel()
    class Message(val message: String) : MovieDetailUiModel()
}