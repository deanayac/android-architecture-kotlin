package com.bootcamp.kotlin.favorites

import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import kotlinx.coroutines.launch

class FavoritePresenter(
    private val favoriteContract: FavoriteContract.View,
    private val repository: FavoriteRepository
) : FavoriteContract.Presenter, Scope by Scope.Impl() {

    override fun initContract() {
        initScope()
    }

    override fun cancelContract() {
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
                favoriteContract.showError("Error al obtener los Datos")
            }
        }
    }
}