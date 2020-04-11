package com.bootcamp.kotlin.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.movies.Movie
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {
    private var movie: Movie? = null
    private var listener: ActionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable(MovieDetailActivity.ARG_MOVIE)
    }

    interface ActionListener {
        fun onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    private fun setupToolbar() {
        with(activity as AppCompatActivity) {
            setSupportActionBar(toolbar)
            setHasOptionsMenu(true)
            supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_TITLE or
                    ActionBar.DISPLAY_SHOW_HOME or
                    ActionBar.DISPLAY_HOME_AS_UP
        }

        addHomeAsUpIndicator()
        appBarLayout.addOnOffsetChangedListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        movie?.let {
            movieHeaderView.setData(it)
        }
    }

    private fun addHomeAsUpIndicator(withBackground: Boolean = true) {
        val arrowBack = if (withBackground) {
            R.drawable.ic_background_arrow_back_white
        } else {
            R.drawable.ic_arrow_back
        }

        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(arrowBack)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                listener?.onBackPressed()
            }
        }

        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActionListener) {
            listener = context as ActionListener
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        var arrowWithBackground = true
        val alphaValueLimit = 0.35f
        val newVerticalOffset = verticalOffset * -1
        val collapseHeight = appBarLayout?.height ?: 0
        val alphaRange = collapseHeight - toolbar.height - 25 * resources.displayMetrics.density
        var alphaValue = 1f

        if (newVerticalOffset >= 0) {
            alphaValue = 1 - newVerticalOffset / alphaRange
        }

        val title = if (alphaValue <= alphaValueLimit) {
            arrowWithBackground = false
            movie?.title ?: ""
        } else {
            ""
        }

        addHomeAsUpIndicator(arrowWithBackground)
        collapsingToolbar.title = title
        movieHeaderView.alpha = alphaValue
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