package com.bootcamp.kotlin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bootcamp.kotlin.data.database.dao.InputSearchDAO
import com.bootcamp.kotlin.data.database.dao.MovieDAO
import com.bootcamp.kotlin.data.database.entity.InputSearch
import com.bootcamp.kotlin.data.database.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val DATA_BASE = "appDatabase.db"

@Database(entities = [Movie::class, InputSearch::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
    abstract fun searchDao(): InputSearchDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATA_BASE
                ).fallbackToDestructiveMigration().addCallback(AppDataBaseCallback()).build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDataBaseCallback() : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.let { appDataBase ->
                        appDataBase.movieDao().deleteAll()
                    }
                }
            }
        }
    }
}