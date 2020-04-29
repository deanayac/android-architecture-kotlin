package com.bootcamp.kotlin.data.server

import com.google.gson.annotations.SerializedName

data class MovieImages(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrops") val backdrops: List<Backdrops>,
    @SerializedName("posters") val posters: List<Posters>
)

data class Backdrops(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("file_path") val filePath: String,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("width") val width: Int
)

data class Posters(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("file_path") val filePath: String,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("width") val width: Int
)