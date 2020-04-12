package com.bootcamp.kotlin.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.kotlin.databinding.FragmentFavoriteBinding
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse
import com.bootcamp.kotlin.networking.ApiClient
import com.bootcamp.kotlin.util.showMessage
import kotlinx.android.synthetic.main.view_progress_bar.*

/**
 * Create by Edmundo
 * 18/03/2020
 * */
class FavoriteFragment : Fragment(), FavoriteContract.View {

    private lateinit var binding: FragmentFavoriteBinding
    private var listener: Listener? = null

    interface Listener {
        fun navigateTo(movieId: Int)
    }

    private val presenter by lazy {
        FavoritePresenter(
            this,
            ApiClient.providerFavoriteRepository()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreateScope()
        presenter.getFavoriteMovies(
            FavoriteMoviesRequest(
                1,
                "d9ae4921794c06bd0fdbd1463d274804",
                "878539e32c923af4c422e5c0b1fa015ba4aa2dfe"
            )
        )
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
        progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = View.GONE
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

    override fun updateData(request: FavoriteMoviesResponse?) {
        request?.results?.let { adapter.moviesList = it }
    }
}