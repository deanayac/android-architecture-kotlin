package com.bootcamp.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response
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

        val token = retrofit.create(Authorization::class.java)
        val call = token.requestToken("5de961ca47ac20a3689205becc3c3b20")

        call.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                val movies = retrofit.create(Movies::class.java)
                val call = movies.popularMovies("5de961ca47ac20a3689205becc3c3b20","1","en-US")

                updateTextView("Primer Callback")

                call.enqueue(object : Callback<MoviesBase> {
                        override fun onFailure(call: Call<MoviesBase>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResponse(
                            call: Call<MoviesBase>,
                            response: Response<MoviesBase>
                        ) {
                            Log.d("Movies", "Total de peliculas ${response.body()!!.results.size}")
                            updateTextView("Ultimo Callback")

                        }
                    })
            }
        })


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