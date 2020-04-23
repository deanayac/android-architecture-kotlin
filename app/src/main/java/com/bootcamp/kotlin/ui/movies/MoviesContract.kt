package com.bootcamp.kotlin.ui.movies

import com.movies.domain.Movie

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<Movie>)
        fun showProgress(isVisible: Boolean)
    }

    interface Presenter {
        fun onCreateScope()
        fun onDestroyScope()
    }
}
