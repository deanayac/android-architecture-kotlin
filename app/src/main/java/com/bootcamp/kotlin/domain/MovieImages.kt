package com.bootcamp.kotlin.domain

/**
 * Created by jhon on 16/04/2020
 */
data class MovieImages (
    val id: Int,
    val backdrops: ArrayList<BackDrops>,
    val posters: ArrayList<Posters>
)

data class BackDrops(
    val aspect_ratio: Int,
    val file_path: String,
    val height: Int,
    val iso_639_1: String?,
    val vote_average: Int,
    val vote_count: Int,
    val width: Int
)

data class Posters(
    val aspect_ratio: Int,
    val file_path: String,
    val height: Int,
    val iso_639_1: String?,
    val vote_average: Int,
    val vote_count: Int,
    val width: Int
)