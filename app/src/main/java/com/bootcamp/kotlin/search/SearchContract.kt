package com.bootcamp.kotlin.search

import com.bootcamp.kotlin.movies.Movie

interface SearchContract {
    interface View{
        fun showMovies(movies: List<Movie>)
        fun showProgress(isVisible: Boolean)
        fun showInputs(inputs:List<String>)
    }

    interface Presenter{
       fun onCreateScope()
       fun onDestroyScope()
       fun searchMovies(description:String)
        fun getInputs()
        fun registerInput(input:String)
    }
}