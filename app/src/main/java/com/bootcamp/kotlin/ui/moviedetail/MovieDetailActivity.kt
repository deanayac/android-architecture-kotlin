package com.bootcamp.kotlin.ui.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity(), MovieDetailFragment.ActionListener {

    companion object {
        const val ARG_MOVIE_ID = "MovieDetailActivity:movieId"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
