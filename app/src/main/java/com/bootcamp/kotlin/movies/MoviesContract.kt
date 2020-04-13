package com.bootcamp.kotlin.movies

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
