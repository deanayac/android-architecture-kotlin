package com.bootcamp.kotlin.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.movies.Movie
import kotlinx.android.synthetic.main.view_movie_header.view.*

class MovieHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_movie_header, this, true)
    }

    fun setData(movie: Movie) {
        imageViewBackground.load("https://image.tmdb.org/t/p/w185/${movie.backdropPath}")
        imageViewPoster.load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        textViewName.text = movie.title
        textViewRelease.text = movie.releaseDate
        textViewAverage.text = movie.voteAverage.toString()
    }
}