package com.bootcamp.kotlin.base

import android.app.Application
import androidx.room.Room
import com.bootcamp.kotlin.BuildConfig
import com.bootcamp.kotlin.database.MovieDatabase
import com.bootcamp.kotlin.util.CrashlyticsTree
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class ApplicationMovies : Application() {
    lateinit var db: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        initTimberWithFirebaseCrashlytics()
        db = Room.databaseBuilder(
            this,MovieDatabase::class.java,"movie_db").build()
    }

    private fun initTimberWithFirebaseCrashlytics() {
        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
            Timber.plant(Timber.DebugTree())
        } else {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            Timber.plant(CrashlyticsTree())
        }
    }
}