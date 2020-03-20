package com.bootcamp.kotlin.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.common.Scope
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.movies.Movies
import com.bootcamp.kotlin.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(), Scope by Scope.Impl() {

    private var listener: Listener? = null

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
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScope()

        launch {
            val api = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .run { create(Movies::class.java) }

            val movies = api.popularMovies(
                apiKey = "5de961ca47ac20a3689205becc3c3b20",
                page = "1",
                language = "en-US"
            )

            adapter.movies = movies.results
        }

        moviesRecyclerView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Listener) {
            listener = context
        }
    }

    override fun onDestroyView() {
        listener = null
        destroyScope()
        super.onDestroyView()
    }
}
