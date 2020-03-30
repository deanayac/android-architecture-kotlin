package com.bootcamp.kotlin.movies

import com.google.gson.annotations.SerializedName

data class MoviesBase(
    val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<Movie>
)
