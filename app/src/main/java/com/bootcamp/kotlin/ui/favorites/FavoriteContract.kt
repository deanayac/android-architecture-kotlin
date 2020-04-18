package com.bootcamp.kotlin.ui.favorites

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
        fun onCreateScope()
        fun onDestroyScope()
        fun getFavoriteMovies(request: FavoriteMoviesRequest)
    }
}
