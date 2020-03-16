package com.bootcamp.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateTextView("Hola Kotlin")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        GlobalScope.launch(Dispatchers.Main) {
            val token = tokenAsync(retrofit)
            val movies = moviesAsync(retrofit)

            updateTextView("${token.await().requestToken} Total de peliculas ${movies.await().results.size}")
        }
    }

    private fun updateTextView(message: String) {
        txtHello.text = message
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
}