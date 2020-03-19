package com.bootcamp.kotlin.welcome

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.favorites.FavoriteFragment
import com.bootcamp.kotlin.home.HomeFragment
import com.bootcamp.kotlin.movies.Movies
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_bottom_navigation.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        events()
        updateTextView("Hola Kotlin")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        GlobalScope.launch(Dispatchers.Main) {
            val token = tokenAsync(retrofit)
            val movies = moviesAsync(retrofit)

//            updateTextView("${token.await().requestToken} Total de peliculas ${movies.await().results.size}")
        }
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
        ll_initFavorites.setOnClickListener {
            changeFragment(FavoriteFragment.newInstance())
        }
    }

    private fun updateTextView(message: String) {
//        txtHello.text = message
    }

    private suspend fun moviesAsync(retrofit: Retrofit) = CoroutineScope(Dispatchers.IO).async {
        val api = retrofit.create(Movies::class.java)
        api.popularMovies(
            apiKey = "5de961ca47ac20a3689205becc3c3b20",
            page = "1",
            language = "en-US"
        )
    }

    private suspend fun tokenAsync(retrofit: Retrofit) = CoroutineScope(Dispatchers.IO).async {
        val api = retrofit.create(Authorization::class.java)
        api.requestToken(apiKey = "5de961ca47ac20a3689205becc3c3b20")
    }

    private fun changeFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main,fragment)
            addToBackStack(null)
        }.commit()
    }
}