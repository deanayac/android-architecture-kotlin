package com.bootcamp.kotlin.ui.favorites

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.util.AndroidHelper
import kotlinx.coroutines.launch

class FavoritePresenter(
    private val favoriteContract: FavoriteContract.View,
    private val repository: FavoriteRepository
) : FavoriteContract.Presenter, Scope by Scope.Impl() {

    override fun onCreateScope() {
        createScope()
    }

    override fun onDestroyScope() {
        destroyScope()
    }

    override fun getFavoriteMovies(request: FavoriteMoviesRequest) {
        favoriteContract.showProgress()
        launch {
            val response = repository.getFavoriteMovies(request)

            if (response != null) {
                favoriteContract.hideProgress()
                favoriteContract.updateData(response)
            } else {
                favoriteContract.hideProgress()
                favoriteContract.showError(AndroidHelper.getString(R.string.error_load_data))
            }
        }
    }
}
