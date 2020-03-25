package com.bootcamp.kotlin.models.network.favoriteMovies

import com.google.gson.annotations.SerializedName

data class FavoriteMoviesResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ResultFavoriteResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

data class ResultFavoriteResponse(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("title")
    val title:String?,
    @SerializedName("poster_path")
    val background:String?)

// path para imagen de portada -> https://image.tmdb.org/t/p/w533_and_h300_bestv2/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg
