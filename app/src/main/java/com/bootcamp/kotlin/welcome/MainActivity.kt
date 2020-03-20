package com.bootcamp.kotlin.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.favorites.FavoriteFragment
import com.bootcamp.kotlin.home.HomeFragment
import com.bootcamp.kotlin.movies.Movie
import kotlinx.android.synthetic.main.custom_bottom_navigation.*

class MainActivity : AppCompatActivity(), HomeFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        events()
        updateTextView("Hola Kotlin")
    }

    private fun events() {
        /**
         * MODIFICADO POR EDMUNDO
         * 18/03/2020
         * Sería bueno IMPLEMENTAR EL PATRÓN DE COMANDO PARA EVITAR EL CÓDIGO REPETITIVO DE SetOnclickListener por cada item dentro del Menú
         * Usar WHEN, no sería recomendable según PRINCIPLES SOLID.
         *
         */
        ll_initHome.setOnClickListener {
            changeFragment(HomeFragment.newInstance())
        }
        ll_initHome.performClick()
        ll_initFavorites.setOnClickListener {
            changeFragment(FavoriteFragment.newInstance())
        }
    }

    private fun updateTextView(message: String) {
//        txtHello.text = message
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun navigateTo(movie: Movie) {
        //TODO navigate to movieDetail
    }
}