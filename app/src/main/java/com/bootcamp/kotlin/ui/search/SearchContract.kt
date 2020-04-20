package com.bootcamp.kotlin.ui.search

import com.bootcamp.kotlin.data.server.Movie
import com.movies.domain.PopularMovie

interface SearchContract {
    interface View{
        fun showMovies(movies: List<PopularMovie>)
        fun showProgress(isVisible: Boolean)
        fun showInputs(inputs:List<String>)
    }

    interface Presenter{
       fun onCreateScope()
       fun onDestroyScope()
       fun searchMovies(description:String)
        fun getInputs()
    }
}