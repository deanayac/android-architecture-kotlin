package com.bootcamp.kotlin.ui.movies

import com.movies.domain.PopularMovie

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<PopularMovie>)
        fun showProgress(isVisible: Boolean)
    }

    interface Presenter {
        fun onCreateScope()
        fun onDestroyScope()
    }
}
