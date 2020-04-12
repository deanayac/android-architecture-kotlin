package com.bootcamp.kotlin.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.databinding.FragmentSearchBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.MoviesRepositoryImpl
import com.bootcamp.kotlin.networking.ApiClient
import com.bootcamp.kotlin.search.adapter.SearchAdapter

class SearchFragment : Fragment(), SearchContract.View {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private var listener: Listener? = null
    private var presenter: SearchContract.Presenter? = null

    private val adapter by lazy {
        SearchAdapter { listener?.navigateTo(it.id) }
    }

    companion object {
        const val START_SEARCH = 3
        @JvmStatic
        fun newInstance(): SearchFragment = SearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        loadingBinding = ViewProgressBarBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SearchPresenter(
            view = this, repository = MoviesRepositoryImpl(
                ApiClient.buildService()
            )
        )

        presenter?.onCreateScope()

        binding.searchEditText.doOnTextChanged { text, _, _, count ->
            if (count >= START_SEARCH) {
                presenter?.searchMovies(text.toString())
            }
        }
    }

    override fun showMovies(movies: List<Movie>) {
        binding.moviesRecyclerView.adapter = adapter
        adapter.movies = movies
    }

    override fun showProgress(isVisible: Boolean) {
        loadingBinding.progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    interface Listener {
        fun navigateTo(movieId: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onDestroyView() {
        presenter?.onDestroyScope()
        super.onDestroyView()
    }
}