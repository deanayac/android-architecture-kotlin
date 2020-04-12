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
    val id:Int,
    @SerializedName("title")
    val title:String?,
    @SerializedName("poster_path")
    val background:String?)

