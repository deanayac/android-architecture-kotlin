package com.bootcamp.kotlin.favorites

import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse

interface FavoriteContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
        fun updateData(request: FavoriteMoviesResponse?)
    }

    interface Presenter {
        fun initContract()
        fun cancelContract()
        fun getFavoriteMovies(request: FavoriteMoviesRequest)
    }

}