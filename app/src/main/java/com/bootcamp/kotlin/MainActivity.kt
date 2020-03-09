package com.bootcamp.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

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
            val token = retrofit.create(Authorization::class.java)
            val call = async(Dispatchers.IO){token.requestToken("5de961ca47ac20a3689205becc3c3b20").execute()}
            val movies = retrofit.create(Movies::class.java)
            val call2 = async(Dispatchers.IO){movies.popularMovies("5de961ca47ac20a3689205becc3c3b20","1","en-US").execute()}
            updateTextView("${call.await().body()!!.request_token} Total de peliculas ${call2.await().body()!!.results.size}")
        }
    }
    private fun updateTextView(message:String) {
        txtHello.text = message
    }
    fun showToast(message:String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}

interface Authorization{
    @GET("3/authentication/token/new")
    fun requestToken(
        @Query("api_key") api_key: String
    ): Call<Token>
}

interface Movies{
    @GET("3/movie/popular")
    fun popularMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: String,
        @Query("language") language: String
    ):Call<MoviesBase>

}