package com.bootcamp.kotlin.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bootcamp.kotlin.data.server.ApiClient
import com.bootcamp.kotlin.data.source.RetrofitDataSource
import com.bootcamp.kotlin.data.source.RoomDataSource
import com.bootcamp.kotlin.databinding.FragmentHomeBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.ui.common.PosterItemDecorator
import com.bootcamp.kotlin.ui.movies.MoviesViewModel.UiModel.*
import com.bootcamp.kotlin.ui.movies.adapter.MoviesAdapter
import com.movies.data.repository.MovieRepositoryImpl
import com.movies.interactor.GetPopularMovies
import kotlinx.android.synthetic.main.view_progress_bar.*

class HomeFragment : Fragment() {

    private var listener: Listener? = null
    private lateinit var binding: FragmentHomeBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

    }

    interface Listener {
        fun navigateTo(movieId: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        loadingBinding = ViewProgressBarBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(
            this, MoviesViewModelFactory(
                GetPopularMovies(
                    MovieRepositoryImpl(
                        RoomDataSource(), RetrofitDataSource(ApiClient.buildService())
                    )
                )
            )
        )[MoviesViewModel::class.java]

        binding.moviesRecyclerView.addItemDecoration(
            PosterItemDecorator(
                binding.moviesRecyclerView.context
            )
        )

        adapter = MoviesAdapter(viewModel::onMovieClicked)
        binding.moviesRecyclerView.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: MoviesViewModel.UiModel) {
        progress.visibility = if (model == Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> {
                adapter.movies = model.movies
            }
            is Navigation -> {
                listener?.navigateTo(model.movie.id)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Listener) {
            listener = context
        }
    }
}