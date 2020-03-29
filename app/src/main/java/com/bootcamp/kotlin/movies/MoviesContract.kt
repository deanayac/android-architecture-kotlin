package com.bootcamp.kotlin.movies

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<Movie>)
        fun showProgress(isVisible: Boolean)
    }

    interface Presenter {
        /**
         * Edmundo
         * 25/03/2020
         * Los metodos no deberían llamarse como los ciclos de un actividad o fragmento
         * ya que en caso se decida cambiar de activity a fragment, entonces se debería de cambiar el
         * nombre del método.
         */
        fun onCreate()
        fun onDestroy()
    }
}
