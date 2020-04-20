package com.bootcamp.kotlin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bootcamp.kotlin.data.database.dao.InputSearchDAO
import com.bootcamp.kotlin.data.database.dao.MovieDAO
import com.bootcamp.kotlin.data.database.entity.InputSearch
import com.bootcamp.kotlin.data.database.entity.Movie

private const val DATA_BASE = "movie_db"

@Database(entities = [Movie::class,InputSearch::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
    abstract fun searchDao(): InputSearchDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATA_BASE
                    )
                        .build()
                    INSTANCE = instance

                    instance
                }
        }
    }
}