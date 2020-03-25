package com.bootcamp.kotlin.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.movies.Movie

class MovieDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie: Movie? = arguments?.getParcelable(MovieDetailActivity.ARG_MOVIE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MovieDetailActivity.ARG_MOVIE, movie)
            }
        }
    }
}