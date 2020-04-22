package com.bootcamp.kotlin.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.ui.common.Constants
import com.bootcamp.kotlin.util.basicDiffUtil
import com.movies.domain.FavoriteMovie
import kotlinx.android.synthetic.main.item_my_favorite_movie.view.*

class FavoriteAdapter(private val listener: (FavoriteMovie) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    var moviesList: List<FavoriteMovie> by basicDiffUtil(
        emptyList(), { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_favorite_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)
        holder.itemView.backgroundImageView.setOnClickListener {
            listener.invoke(movie)
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(movie: FavoriteMovie) {
            itemView.movieNameTextView.text = movie.title
            itemView.backgroundImageView.load(getUri(movie.posterPath)) {
                crossfade(true)
            }
        }

        private fun getUri(movie: String?): String {
            return Constants.PATH_MOVIE.plus(movie)
        }
    }
}