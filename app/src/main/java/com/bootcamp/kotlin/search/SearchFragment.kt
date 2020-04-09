package com.bootcamp.kotlin.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.databinding.FragmentSearchBinding
import com.bootcamp.kotlin.networking.ApiClient
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.MoviesRepositoryImpl
import com.bootcamp.kotlin.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.view_progress_bar.*

class SearchFragment : Fragment(), SearchContract.View{

    private lateinit var binding:FragmentSearchBinding
    private var listener:Listener? = null
    private var presenter:SearchContract.Presenter?=null

   companion object{
       @JvmStatic
       fun newInstance():SearchFragment = SearchFragment()
   }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SearchPresenter(view = this, repository = MoviesRepositoryImpl(
            ApiClient.buildService()))
        presenter?.initView()
        search_btn.setOnClickListener{
            presenter?.searchMovies(search_src_text.text.toString())
        }
    }

    override fun showMovies(movies: List<Movie>) {
        binding.moviesRecyclerView.adapter = adapter
        adapter.movies = movies
    }

    override fun showProgress(isVisible: Boolean) {
        progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    interface Listener {
        fun navigateTo(movie: Movie)
    }

    private val adapter = MoviesAdapter {
        listener?.navigateTo(it)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragment.Listener) {
            listener = context
        }
    }

    override fun onDestroyView() {
        listener = null
        super.onDestroyView()
    }
}
