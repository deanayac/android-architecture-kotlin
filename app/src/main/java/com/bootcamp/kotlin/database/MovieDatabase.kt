package com.bootcamp.kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class,InputSearch::class], version = 1,exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO

    abstract  fun inputSearch(): InputSearchDAO

}