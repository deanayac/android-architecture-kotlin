package com.bootcamp.kotlin.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.movies.Movie

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val ARG_MOVIE = "MovieDetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie: Movie? = intent.getParcelableExtra(ARG_MOVIE)

        if (savedInstanceState == null) {
            if (movie == null) finish()

            val fragment = MovieDetailFragment.newInstance(movie!!)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayoutMain, fragment)
                .commit()
        }
    }
}
