package com.bootcamp.kotlin.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.databinding.FragmentFavoriteBinding
import com.bootcamp.kotlin.networking.ApiClient
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesRequest
import com.bootcamp.kotlin.models.network.favoriteMovies.FavoriteMoviesResponse
import com.bootcamp.kotlin.util.showMessage
import kotlinx.android.synthetic.main.view_progress_bar.*

/**
 * Create by Edmundo
 * 18/03/2020
 * */
class FavoriteFragment : Fragment(), FavoriteContract.View {

    private lateinit var binding: FragmentFavoriteBinding
    private val presenter by lazy {
        FavoritePresenter(
            this,
            ApiClient.providerFavoriteRepository()
        )
    }
    private var adapter = FavoriteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initContract()
        presenter.getFavoriteMovies(
            FavoriteMoviesRequest(
                1,
                "d9ae4921794c06bd0fdbd1463d274804",
                "878539e32c923af4c422e5c0b1fa015ba4aa2dfe"
            )
        )
        binding.favoriteMoviesRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.cancelContract()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError(message: String?) {
        message?.let {
            activity?.showMessage(it)
        }
    }

    override fun updateData(request: FavoriteMoviesResponse?) {
        request?.results?.let { adapter.moviesList = it }
    }
}
