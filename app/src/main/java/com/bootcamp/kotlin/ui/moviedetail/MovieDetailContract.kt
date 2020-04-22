package com.bootcamp.kotlin.ui.moviedetail

import com.movies.domain.Movie
import com.movies.domain.MovieImages

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