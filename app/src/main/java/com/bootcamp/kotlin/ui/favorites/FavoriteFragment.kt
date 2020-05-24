import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.kotlin.databinding.FragmentFavoriteBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.ui.favorites.FavoriteAdapter
import com.bootcamp.kotlin.ui.favorites.FavoriteUiModel
import com.bootcamp.kotlin.ui.favorites.FavoriteUiModel.Content
import com.bootcamp.kotlin.ui.favorites.FavoriteUiModel.Loading
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private var listener: Listener? = null
    private val viewModel: FavoriteViewModel by lifecycleScope.viewModel(this)
    private lateinit var adapter: FavoriteAdapter

    interface Listener {
        fun navigateTo(movieId: Int)
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
        adapter = FavoriteAdapter(viewModel::onMovieClicked)
        binding.favoriteMoviesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoriteMoviesRecyclerView.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.navigation.observe(this, Observer {
            it.getContentIfNotHandled()?.let { favoriteMovie ->
                listener?.navigateTo(favoriteMovie.id)
            }
        })
    }

    private fun updateUi(model: FavoriteUiModel) {
        progress.visibility = if (model == Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> {
                adapter.moviesList = model.favoriteMovies
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context as Listener
        }
    }
}