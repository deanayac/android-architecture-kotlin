package com.bootcamp.kotlin.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.domain.Movie
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
        imageViewBackground.load("${Constants.PATH_MOVIE_W185}${movie.backDropPath}")
        imageViewPoster.load("${Constants.PATH_MOVIE_W185}${movie.posterPath}")
        imageViewStar.visibility = View.VISIBLE
        textViewName.text = movie.title
        textViewRelease.text = movie.releaseDate
        textViewAverage.text = movie.voteAverage.toString()
    }
}