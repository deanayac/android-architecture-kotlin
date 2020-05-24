package com.bootcamp.kotlin.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.util.AndroidHelper
import com.movies.data.common.Resource
import com.movies.data.common.Status.ERROR
import com.movies.data.common.Status.SUCCESS
import com.movies.domain.Movie
import com.movies.domain.MovieImages
import com.movies.interactor.GetMovieDetail
import com.movies.interactor.GetMovieDetailImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailViewModel(
    private val getMovieDetail: GetMovieDetail,
    private val getMovieDetailImages: GetMovieDetailImages,
    private val movieId: Int
) : ViewModel(), Scope by Scope.Impl() {

    init {
        createScope()
    }

    private val _model = MutableLiveData<MovieDetailUiModel>()
    val model: LiveData<MovieDetailUiModel>
        get() {
            if (_model.value == null) {
                loadData(movieId)
            }

            return _model
        }

    private fun loadData(movieId: Int) {
        launch {
            _model.value = MovieDetailUiModel.Loading

            val movie = async(Dispatchers.IO) {
                getMovieDetail.invoke(movieId)
            }

            val movieImage = async(Dispatchers.IO) {
                getMovieDetailImages.invoke(movieId)
            }

            showMovieDetail(movie.await())
            showMovieImages(movieImage.await())
        }
    }

    private fun showMovieDetail(movie: Resource<Movie>) {
        when (movie.status) {
            SUCCESS -> {
                movie.data?.let {
                    _model.value = MovieDetailUiModel.Header(it)
                } ?: run {
                    _model.value =
                        MovieDetailUiModel.Message(AndroidHelper.getString(R.string.error_show_information))
                }
            }
            ERROR -> {
                _model.value =
                    MovieDetailUiModel.Message(AndroidHelper.getString(R.string.error_load_data))
                Timber.e(movie.message)
            }
        }
    }

    private fun showMovieImages(movieImages: Resource<MovieImages>) {
        when (movieImages.status) {
            SUCCESS -> {
                movieImages.data?.let {
                    _model.value = MovieDetailUiModel.Posters(it)
                } ?: run {
                    _model.value =
                        MovieDetailUiModel.Message(AndroidHelper.getString(R.string.error_show_information))
                }
            }
            ERROR -> {
                _model.value =
                    MovieDetailUiModel.Message(AndroidHelper.getString(R.string.error_load_data))
                Timber.e(movieImages.message)
            }
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}