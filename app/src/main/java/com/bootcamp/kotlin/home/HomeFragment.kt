package com.bootcamp.kotlin.home
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.databinding.FragmentHomeBinding
import com.bootcamp.kotlin.di.ApiClient
import com.bootcamp.kotlin.movies.*
import com.bootcamp.kotlin.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.view_progress_bar.*

class HomeFragment : Fragment(), MoviesContract.View {

    private var listener: Listener? = null
    private var presenter: MoviesContract.Presenter? = null
    private lateinit var binding: FragmentHomeBinding

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        const val API_KEY = "5de961ca47ac20a3689205becc3c3b20"
    }

    interface Listener {
        fun navigateTo(movie: Movie)
    }

    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MoviesAdapter { listener?.navigateTo(it) }

        binding.moviesRecyclerView.adapter = adapter

        presenter = MoviesPresenter(
            view = this,
            repository = MoviesRepositoryImpl(ApiClient.buildService(), API_KEY)
        )

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
        presenter?.onDestroy()
        super.onDestroyView()
    }
}
