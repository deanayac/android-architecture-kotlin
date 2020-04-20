package com.bootcamp.kotlin.ui.favorites

import com.bootcamp.kotlin.networking.ApiClient
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse
import com.bootcamp.kotlin.data.server.MovieDbServices

class FavoriteNetwork : FavoriteRepository {
    override suspend fun getFavoriteMovies(request: FavoriteMoviesRequest): FavoriteMoviesResponse? {
        return ApiClient.buildService().run { create(MovieDbServices::class.java) }
            .getFavoriteMovies(request.accounId, request.apiKey, request.sessionId)
    }
}
