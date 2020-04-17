package com.bootcamp.kotlin.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.domain.Movie
import com.bootcamp.kotlin.domain.MovieImages
import kotlinx.android.synthetic.main.view_movie_footer.view.*

/**
 * Created by jhon on 16/04/2020
 */
class RecyclerViewFooterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_movie_footer, this, true)
    }

    fun setImage(movieImages: MovieImages) {
        imageDetail.load("${Constants.PATH_MOVIE_W185}${movieImages.posters}")
    }
}