package com.bootcamp.kotlin.moviedetail

import com.bootcamp.kotlin.domain.Movie
import com.bootcamp.kotlin.domain.MovieImages

interface MovieDetailContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showMessage(message: String)
        fun showMovieDetail(movie: Movie)
        fun showMovieImages(moviesImages: MovieImages)
    }

    interface Presenter {
        fun loadData(movieId: Int)
        fun onDestroyScope()
        fun onCreateScope()
    }
}