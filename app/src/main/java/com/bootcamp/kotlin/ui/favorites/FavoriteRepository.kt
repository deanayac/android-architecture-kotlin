package com.bootcamp.kotlin.ui.favorites

import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse

interface FavoriteRepository {
    suspend fun getFavoriteMovies(request: FavoriteMoviesRequest): FavoriteMoviesResponse?
}
