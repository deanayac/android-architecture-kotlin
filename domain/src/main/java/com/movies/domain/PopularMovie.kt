package com.movies.domain

data class PopularMovie(
    val backdropPath: String?,
    val genresIds: List<Int>,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double
)