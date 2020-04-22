package com.movies.domain

data class MovieImages (
    val id: Int,
    val backdrops: List<Backdrops>,
    val posters: List<Posters>
)

data class Backdrops(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String?,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)

data class Posters(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String?,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)