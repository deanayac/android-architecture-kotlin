package com.bootcamp.kotlin.movieDetail

import com.bootcamp.kotlin.domain.Movie
import com.bootcamp.kotlin.domain.MovieImages

interface MovieDetailContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showMessage(message: String)
        fun showMovieDetail(movie: Movie)
        fun showMovieImages(movieImages: MovieImages)
    }

    interface Presenter {
        fun loadData(movieId: Int)
        fun loadImage(movieId: Int)
        fun onDestroyScope()
        fun onCreateScope()
    }
}