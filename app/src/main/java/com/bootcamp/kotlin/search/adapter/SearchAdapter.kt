package com.bootcamp.kotlin.search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.databinding.ItemSearchMovieBinding
import com.bootcamp.kotlin.movies.Movie
import com.bootcamp.kotlin.util.inflate
import kotlin.properties.Delegates

private typealias MovieListener = (Movie) -> Unit

class SearchAdapter(items: List<Movie> = emptyList(),private val listener:MovieListener) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var movies: List<Movie> by Delegates.observable(items){_,_,_ -> notifyDataSetChanged()}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_search_movie,false)
        return ViewHolder(view,listener)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(movies[position])
    }

    class ViewHolder(view: View, private val listener:MovieListener) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemSearchMovieBinding.bind(view)

        fun bin(movie: Movie) {
            with(binding){
                root.setOnClickListener { listener(movie) }
                title.text = movie.title
                movieThumb.load("${Constants.PATH_MOVIE_W185.plus(movie.backdropPath)}")
            }
        }
    }
}



