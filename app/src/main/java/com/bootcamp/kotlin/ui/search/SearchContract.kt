package com.bootcamp.kotlin.ui.search

import com.bootcamp.kotlin.data.server.Movie

interface SearchContract {
    interface View{
        fun showMovies(movies: List<Movie>)
        fun showProgress(isVisible: Boolean)
    }

    interface Presenter{
       fun onCreateScope()
        fun onDestroyScope()
       fun searchMovies(description:String)
    }
}