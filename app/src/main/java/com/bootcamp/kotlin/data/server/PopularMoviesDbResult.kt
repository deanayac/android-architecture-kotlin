package com.bootcamp.kotlin.data.server

import com.google.gson.annotations.SerializedName

data class PopularMoviesDbResult(
    val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<Movie>
)