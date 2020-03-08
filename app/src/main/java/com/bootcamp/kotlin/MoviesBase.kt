package com.bootcamp.kotlin

data class MoviesBase (
val page : Int,
val total_results : Int,
val total_pages : Int,
val results : List<MoviesData>
)