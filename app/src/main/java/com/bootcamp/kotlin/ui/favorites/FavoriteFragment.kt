import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.kotlin.data.source.RetrofitDataSource
import com.bootcamp.kotlin.data.source.RoomDataSource
import com.bootcamp.kotlin.databinding.FragmentFavoriteBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.data.server.ApiClient
import com.bootcamp.kotlin.ui.favorites.FavoriteAdapter
import com.bootcamp.kotlin.ui.favorites.FavoriteContract
import com.bootcamp.kotlin.util.showMessage
import com.movies.data.repository.MovieRepositoryImpl
import com.movies.domain.FavoriteMovie
import com.movies.interactor.GetFavoriteMovies

class FavoriteFragment : Fragment(), FavoriteContract.View {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private var listener: Listener? = null

    interface Listener {
        fun navigateTo(movieId: Int)
    }

    private val presenter by lazy {
        FavoritePresenter(
            this,
            GetFavoriteMovies(
                MovieRepositoryImpl(
                    RoomDataSource(),
                    RetrofitDataSource(ApiClient.buildService())
                )
            )
        )
    }

    private val adapter by lazy {
        FavoriteAdapter { movie ->
            listener?.navigateTo(movie.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadingBinding = ViewProgressBarBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreateScope()
        presenter.getFavoriteMovies()
        binding.favoriteMoviesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoriteMoviesRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        presenter.onCreateScope()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }

    override fun showProgress() {
        loadingBinding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBinding.progress.visibility = View.GONE
    }

    override fun showError(message: String?) {
        activity?.showMessage(message)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context as Listener
        }
    }

    override fun updateData(favoriteMovies: List<FavoriteMovie>) {
        adapter.moviesList = favoriteMovies
    }
}