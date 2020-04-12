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
import com.bootcamp.kotlin.domain.Movie
import com.bootcamp.kotlin.movies.MoviesRepositoryImpl
import com.bootcamp.kotlin.networking.ApiClient
import com.bootcamp.kotlin.util.showMessage
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.view_progress_bar.*

const val DEFAULT_MOVIE_ID = 0

class MovieDetailFragment : Fragment(),
    MovieDetailContract.View,
    AppBarLayout.OnOffsetChangedListener
{
    private var movieId: Int = 0
    private var listener: ActionListener? = null
    private var presenter: MovieDetailPresenter? = null
    private var movieTitle = ""

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(MovieDetailActivity.ARG_MOVIE_ID, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt(MovieDetailActivity.ARG_MOVIE_ID) ?: DEFAULT_MOVIE_ID
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
        presenter = MovieDetailPresenter(this, MoviesRepositoryImpl(ApiClient.buildService()))
        presenter?.onCreateScope()
        presenter?.loadData(movieId)
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

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
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
            movieTitle
        } else {
            ""
        }

        addHomeAsUpIndicator(arrowWithBackground)
        collapsingToolbar.title = title
        movieHeaderView.alpha = alphaValue
    }

    override fun showMessage(message: String) {
        context?.showMessage(message)
    }

    override fun onDestroyView() {
        presenter?.onDestroyScope()
        super.onDestroyView()
    }

    override fun showMovieDetail(movie: Movie) {
        movieTitle = movie.title
        movieHeaderView.setData(movie)
        expandableTextViewDescription.setData(movie.overview)
    }
}