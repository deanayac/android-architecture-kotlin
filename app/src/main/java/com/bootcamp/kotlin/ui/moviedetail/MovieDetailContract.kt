package com.bootcamp.kotlin.ui.moviedetail

import com.movies.domain.Movie

interface MovieDetailContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showMessage(message: String)
        fun showMovieDetail(movie: Movie)
    }

    interface Presenter {
        fun loadData(movieId: Int)
        fun onDestroyScope()
        fun onCreateScope()
    }
}