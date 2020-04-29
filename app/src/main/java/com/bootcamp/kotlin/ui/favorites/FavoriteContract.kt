package com.bootcamp.kotlin.ui.favorites

import com.movies.domain.FavoriteMovie

interface FavoriteContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
        fun updateData(favoriteMovies: List<FavoriteMovie>)
    }

    interface Presenter {
        fun onCreateScope()
        fun onDestroyScope()
        fun getFavoriteMovies()
    }
}
