package com.bootcamp.kotlin.search

import com.bootcamp.kotlin.movies.Movie

interface SearchContract {
    interface View{
        fun showMovies(movies: List<Movie>)
        fun showProgress(isVisible: Boolean)
    }

    interface Presenter{
       fun initView()
       fun searchMovies(description:String)
    }
}
