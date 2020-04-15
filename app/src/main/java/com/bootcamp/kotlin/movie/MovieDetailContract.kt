package com.bootcamp.kotlin.movie

import com.bootcamp.kotlin.domain.Movie

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