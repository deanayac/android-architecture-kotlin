package com.bootcamp.kotlin.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.movies.Movie

class MovieDetailActivity : AppCompatActivity(), MovieDetailFragment.ActionListener {

    companion object {
        const val ARG_MOVIE_ID = "MovieDetailActivity:movieId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra(ARG_MOVIE_ID, 0)

        if (savedInstanceState == null) {
            if (movieId == 0) finish()

            val fragment = MovieDetailFragment.newInstance(movieId)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayoutMain, fragment)
                .commit()

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
