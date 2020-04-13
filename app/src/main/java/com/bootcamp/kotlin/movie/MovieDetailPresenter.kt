package com.bootcamp.kotlin.movie

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.domain.Movie
import com.bootcamp.kotlin.movies.MoviesRepository
import com.bootcamp.kotlin.networking.Resource
import com.bootcamp.kotlin.networking.Status.ERROR
import com.bootcamp.kotlin.networking.Status.SUCCESS
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