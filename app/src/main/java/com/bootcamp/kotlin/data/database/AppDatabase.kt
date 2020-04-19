package com.bootcamp.kotlin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE = "movie_db"

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATA_BASE
        ).build()
    }
}