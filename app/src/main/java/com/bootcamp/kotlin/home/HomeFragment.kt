package com.bootcamp.kotlin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.databinding.FragmentHomeBinding
import com.bootcamp.kotlin.main.MainActivity
import com.bootcamp.kotlin.movies.*
import com.bootcamp.kotlin.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.view_progress_bar.*

class HomeFragment : Fragment(), MoviesContract.View {

    private var presenter: MoviesContract.Presenter? = null
    private lateinit var repository: MoviesRepository
    private lateinit var binding: FragmentHomeBinding
    private var adapter = MoviesAdapter(selectedItem())

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesRecyclerView.adapter = adapter
        repository = MoviesRepositoryImpl()
        presenter = MoviesPresenter(view = this, repository = repository)
        presenter?.onCreate()
    }

    override fun showMovies(movies: List<Movie>) {
        adapter.movies = movies
    }

    override fun showProgress(isVisible: Boolean) {
        progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        presenter?.onDestroy()
        super.onDestroyView()
    }

    private fun selectedItem(): (Movie) -> Unit {
        return {
            ((activity as MainActivity).navigateTo(it))
        }
    }
}
