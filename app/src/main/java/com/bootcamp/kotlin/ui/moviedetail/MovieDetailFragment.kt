package com.bootcamp.kotlin.ui.moviedetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.FragmentMovieDetailBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.ui.moviedetail.MovieDetailUiModel.*
import com.bootcamp.kotlin.util.showMessage
import com.google.android.material.appbar.AppBarLayout
import com.movies.domain.Movie
import com.movies.domain.MovieImages
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

const val DEFAULT_MOVIE_ID = 0

class MovieDetailFragment : Fragment(),
    AppBarLayout.OnOffsetChangedListener {
    private var movieId: Int = 0
    private var listener: ActionListener? = null
    private var movieTitle = ""
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private lateinit var adapter: MovieDetailAdapter
    private val viewModel: MovieDetailViewModel by lifecycleScope.viewModel(this) {
        parametersOf(movieId)
    }

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
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        loadingBinding = ViewProgressBarBinding.bind(binding.root)
        return binding.root
    }

    private fun setupToolbar() {
        with(activity as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
            supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_TITLE or
                    ActionBar.DISPLAY_SHOW_HOME or
                    ActionBar.DISPLAY_HOME_AS_UP
        }

        addHomeAsUpIndicator()
        binding.appBarLayout.addOnOffsetChangedListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        adapter = MovieDetailAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewBrackgrounds.layoutManager = layoutManager
        recyclerViewBrackgrounds.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: MovieDetailUiModel) {
        loadingBinding.progress.visibility =
            if (model is Loading) View.VISIBLE else View.GONE

        when (model) {
            is Header -> {
                showMovieDetail(model.movie)
            }
            is Posters -> {
                showMovieImages(model.moviesImages)
            }
            is Message -> {
                context?.showMessage(model.message)
            }
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
        val alphaRange =
            collapseHeight - binding.toolbar.height - 25 * resources.displayMetrics.density
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
        binding.collapsingToolbar.title = title
        binding.movieHeaderView.alpha = alphaValue
    }

    private fun showMovieDetail(movie: Movie) {
        movieTitle = movie.title
        binding.movieHeaderView.setData(movie)
        binding.expandableTextViewDescription.setData(movie.overview)
    }

    private fun showMovieImages(moviesImages: MovieImages) {
        adapter.movieImages = moviesImages.backdrops
    }
}