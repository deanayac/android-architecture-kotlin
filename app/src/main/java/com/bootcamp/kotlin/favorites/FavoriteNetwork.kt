package com.bootcamp.kotlin.favorites

import com.bootcamp.kotlin.di.ApiClient
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse

class FavoriteNetwork:FavoriteRepository {
    override suspend fun getFavoriteMovies(request: FavoriteMoviesRequest): FavoriteMoviesResponse? {
        return ApiClient.buildService()?.getFavoriteMovies(request.accounId,request.apiKey,request.sessionId)
    }
}