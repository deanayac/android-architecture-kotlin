package com.bootcamp.kotlin.data.server

import com.google.gson.annotations.SerializedName

/**
 * Created by jhon on 16/04/2020
 */
data class MovieImages(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrops") val backdrops: List<Backdrops>,
    @SerializedName("posters") val posters: List<Posters>
)

data class Backdrops(
    @SerializedName("aspect_ratio") val aspect_ratio: Double,
    @SerializedName("file_path") val file_path: String,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("width") val width: Int
)

data class Posters(
    @SerializedName("aspect_ratio") val aspect_ratio: Double,
    @SerializedName("file_path") val file_path: String,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("width") val width: Int
)