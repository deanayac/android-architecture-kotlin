package com.bootcamp.kotlin.moviedetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.domain.Backdrops
import com.bootcamp.kotlin.util.basicDiffUtil
import com.bootcamp.kotlin.util.inflate
import kotlinx.android.synthetic.main.view_movie_footer.view.*

/**
 * Created by jhon on 18/04/2020
 */
class MovieDetailAdapter :
    RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>() {

    var movieImages: List<Backdrops> by basicDiffUtil(
        emptyList(), { old, new -> old.file_path == new.file_path }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_movie_footer, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieImages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieImage = movieImages[position]
        holder.bind(movieImage)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieImages: Backdrops) {
            itemView.imageViewBackground.load(getUri(movieImages.file_path)) {
                crossfade(true)
            }
        }

        private fun getUri(movie:String?):String{
            return Constants.PATH_MOVIE_W500.plus(movie)
        }
    }
}