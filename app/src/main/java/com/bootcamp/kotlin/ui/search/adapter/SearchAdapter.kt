package com.bootcamp.kotlin.ui.search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.ui.common.Constants
import com.bootcamp.kotlin.databinding.ItemSearchMovieBinding
import com.bootcamp.kotlin.util.inflate
import com.movies.domain.PopularMovie
import kotlin.properties.Delegates

private typealias MovieListener = (PopularMovie) -> Unit

class SearchAdapter(items: List<PopularMovie> = emptyList(), private val listener: MovieListener) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var movies: List<PopularMovie> by Delegates.observable(items) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_search_movie, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(movies[position])
    }

    class ViewHolder(view: View, private val listener: MovieListener) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemSearchMovieBinding.bind(view)

        fun bin(movie: PopularMovie) {
            with(binding) {
                root.setOnClickListener { listener(movie) }
                title.text = movie.title
                movieThumb.load(Constants.PATH_MOVIE_W300.plus(movie.backdropPath))
            }
        }
    }
}