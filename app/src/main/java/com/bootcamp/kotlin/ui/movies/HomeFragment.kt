package com.bootcamp.kotlin.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bootcamp.kotlin.databinding.FragmentHomeBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.ui.common.PosterItemDecorator
import com.bootcamp.kotlin.ui.movies.MovieUiModel.Content
import com.bootcamp.kotlin.ui.movies.MovieUiModel.Loading
import com.bootcamp.kotlin.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class HomeFragment : Fragment() {

    private var listener: Listener? = null
    private lateinit var binding: FragmentHomeBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private val viewModel: MoviesViewModel by lifecycleScope.viewModel(this)
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

        binding.moviesRecyclerView.addItemDecoration(
            PosterItemDecorator(
                binding.moviesRecyclerView.context
            )
        )

        adapter = MoviesAdapter(viewModel::onMovieClicked)
        binding.moviesRecyclerView.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.navigation.observe(this, Observer {
            it.getContentIfNotHandled()?.let {movie ->
                listener?.navigateTo(movie.id)
            }
        })
    }

    private fun updateUi(model: MovieUiModel) {
        progress.visibility = if (model == Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> {
                adapter.movies = model.movies
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