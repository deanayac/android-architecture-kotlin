package com.bootcamp.kotlin.data.server

import com.google.gson.annotations.SerializedName

/**
 * Created by jhon on 16/04/2020
 */
data class MoviesImages(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrops") val backdrops: ArrayList<BackDrops>,
    @SerializedName("posters") val posters: ArrayList<Posters>
)

data class BackDrops(
    @SerializedName("aspect_ratio") val aspect_ratio: Int,
    @SerializedName("file_path") val file_path: String,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("vote_average") val vote_average: Int,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("width") val width: Int
)

data class Posters(
    @SerializedName("aspect_ratio") val aspect_ratio: Int,
    @SerializedName("file_path") val file_path: String,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("vote_average") val vote_average: Int,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("width") val width: Int
)