package com.bootcamp.kotlin.ui.favorites

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.util.AndroidHelper
import com.movies.interactor.GetFavoriteMovies
import kotlinx.coroutines.launch

class FavoritePresenter(
    private val favoriteContract: FavoriteContract.View,
    private val getFavoriteMovies: GetFavoriteMovies
) : FavoriteContract.Presenter, Scope by Scope.Impl() {

    override fun onCreateScope() {
        createScope()
    }

    override fun onDestroyScope() {
        destroyScope()
    }

    override fun getFavoriteMovies() {
        favoriteContract.showProgress()
        launch {
            val response = getFavoriteMovies.invoke()

            if (response != null) {
                favoriteContract.hideProgress()
                favoriteContract.updateData(response.data!!)
            } else {
                favoriteContract.hideProgress()
                favoriteContract.showError(AndroidHelper.getString(R.string.error_load_data))
            }
        }
    }
}
