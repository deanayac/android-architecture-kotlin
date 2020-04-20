package com.bootcamp.kotlin.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.movies.domain.PopularMovie
import kotlinx.android.synthetic.main.view_movie.view.*
import kotlin.properties.Delegates

class MoviesAdapter(private val listener: (PopularMovie) -> Unit): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var movies: List<PopularMovie> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object: DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition].id == new[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] == new[newItemPosition]
            }

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listener(movie)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(movie: PopularMovie) {
            itemView.posterImageView.load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }
}
