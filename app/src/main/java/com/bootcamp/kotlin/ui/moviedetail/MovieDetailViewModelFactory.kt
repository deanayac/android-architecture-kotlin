package com.bootcamp.kotlin.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.interactor.GetMovieDetail
import com.movies.interactor.GetMovieDetailImages

@Suppress("UNCHECKED_CAST")
class MovieDetailViewModelFactory(
    private val getMovieDetail: GetMovieDetail,
    private val getMovieDetailImages: GetMovieDetailImages,
    private val movieId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(getMovieDetail, getMovieDetailImages, movieId) as T
    }
}