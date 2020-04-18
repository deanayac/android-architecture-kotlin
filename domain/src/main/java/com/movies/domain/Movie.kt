package com.movies.domain

data class Movie(
    val backDropPath: String?,
    val genres: ArrayList<Genre>,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double
)

data class Genre(val id: Int, val name: String)