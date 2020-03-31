package com.bootcamp.kotlin.home
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.databinding.FragmentHomeBinding
import com.bootcamp.kotlin.movies.MoviesContract
import com.bootcamp.kotlin.movies.MoviesPresenter
import com.bootcamp.kotlin.movies.MoviesRepositoryImpl
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.view_progress_bar.*

class HomeFragment : Fragment(), MoviesContract.View {

    private var listener: Listener? = null
    private var presenter: MoviesContract.Presenter? = null
    private lateinit var binding: FragmentHomeBinding

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    interface Listener {
        fun navigateTo(movie: Movie)
    }

    private val adapter = MoviesAdapter {
        listener?.navigateTo(it)
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
        presenter = MoviesPresenter(view = this, repository = MoviesRepositoryImpl())
        presenter?.onCreate()
    }

    override fun showMovies(movies: List<Movie>) {
        adapter.movies = movies
    }

    override fun showProgress(isVisible: Boolean) {
        progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Listener) {
            listener = context
        }
    }

    override fun onDestroyView() {
        listener = null
        presenter?.onDestroy()
        super.onDestroyView()
    }
}
