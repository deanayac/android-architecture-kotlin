package com.bootcamp.kotlin.ui.search

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bootcamp.kotlin.databinding.FragmentSearchBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.ui.search.adapter.SearchAdapter
import com.bootcamp.kotlin.util.AndroidHelper
import com.bootcamp.kotlin.util.showMessage
import com.movies.domain.Movie
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class SearchFragment : Fragment(){

    private lateinit var binding: FragmentSearchBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private var listener: Listener? = null

    private val viewModel: SearchViewModel by lifecycleScope.viewModel(this)

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

        viewModel.model.observe(this,Observer(::updateUi))

        binding.searchEditText.doOnTextChanged { text, _, _, count ->
            if (count >= START_SEARCH) {
                viewModel.searchMovies(text.toString())
            } else {
                viewModel.getInputs()
            }
        }
    }

     fun showMovies(movies: List<Movie>) {
        binding.moviesRecyclerView.adapter = adapter
        adapter.movies = movies
    }

     fun showInputs(inputs: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            context!!,
            R.layout.simple_dropdown_item_1line, inputs
        )
        binding.searchEditText.setAdapter(adapter)
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

    private fun updateUi(model:SearchViewModel.UiModel){
        loadingBinding.progress.visibility =
            if (model is SearchViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when(model){
            is SearchViewModel.UiModel.SearchMovie -> showMovies(model.movies)
            is SearchViewModel.UiModel.Autocomplete -> showInputs(model.inputs)
            is SearchViewModel.UiModel.showError -> activity?.showMessage(AndroidHelper.getString(
                com.bootcamp.kotlin.R.string.error_show_information))
        }
    }
}