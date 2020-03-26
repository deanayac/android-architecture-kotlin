package com.bootcamp.kotlin.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.models.network.favoriteMovies.ResultFavoriteResponse
import com.bootcamp.kotlin.util.basicDiffUtil
import kotlinx.android.synthetic.main.item_my_favorite_movie.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    var moviesList: List<ResultFavoriteResponse> by basicDiffUtil(
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
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(movie: ResultFavoriteResponse) {
            itemView.nameMovie_textView.text = movie.title
            itemView.backgroundMyFavorite_imageView.load("https://image.tmdb.org/t/p/w533_and_h300_bestv2/${movie.background}") {
                crossfade(true)
            }
        }
    }
}