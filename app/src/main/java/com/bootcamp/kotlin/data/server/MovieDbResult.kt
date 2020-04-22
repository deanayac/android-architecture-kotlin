package com.bootcamp.kotlin.data.server

import com.google.gson.annotations.SerializedName

data class MovieDbResult(
    @SerializedName("backdrop_path") val backDropPath: String?,
    val genres: ArrayList<Genre>,
    val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseData: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double
)

data class Genre(val id: Int, val name: String)