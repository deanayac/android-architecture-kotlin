package com.movies.domain

data class MovieImages (
    val id: Int,
    val backdrops: List<Backdrops>,
    val posters: List<Posters>
)

data class Backdrops(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso_639_1: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)

data class Posters(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso_639_1: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)