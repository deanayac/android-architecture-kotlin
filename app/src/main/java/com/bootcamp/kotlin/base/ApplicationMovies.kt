package com.bootcamp.kotlin.base

import android.app.Application
import androidx.room.Room
import com.bootcamp.kotlin.database.MovieDatabase
import com.bootcamp.kotlin.util.AndroidHelper

class ApplicationMovies : Application() {
    lateinit var db: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        AndroidHelper.init(applicationContext)
        db = Room.databaseBuilder(
            this,MovieDatabase::class.java,"movie_db").build()
    }
}