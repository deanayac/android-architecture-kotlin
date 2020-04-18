package com.bootcamp.kotlin.ui.moviedetail

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.data.repositories.MoviesRepository
import com.bootcamp.kotlin.ui.common.Scope
import com.movies.domain.Movie
import com.movies.data.common.Resource
import com.movies.data.common.Status.ERROR
import com.movies.data.common.Status.SUCCESS
import com.bootcamp.kotlin.util.AndroidHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailPresenter(
    private val view: MovieDetailContract.View?,
    private val moviesRepository: MoviesRepository
) : MovieDetailContract.Presenter, Scope by Scope.Impl() {

    override fun loadData(movieId: Int) {
        launch {
            view?.showProgress()
            val movie = async(Dispatchers.IO) {
                moviesRepository.movie(movieId)
            }
            showMovieDetail(movie.await())
        }
    }

    private fun showMovieDetail(movie: Resource<Movie>) {
        when (movie.status) {
            SUCCESS -> {
                movie.data?.let {
                    view?.showMovieDetail(it)
                } ?: run {
                    view?.showMessage(AndroidHelper.getString(R.string.error_show_information))
                }
            }
            ERROR -> {
                view?.showMessage(AndroidHelper.getString(R.string.error_load_data))
                Timber.e(movie.message)
            }
        }

        view?.hideProgress()
    }

    override fun onDestroyScope() {
        destroyScope()
    }

    override fun onCreateScope() {
        createScope()
    }
}