package com.bootcamp.kotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InputSearchDAO {
    @Query("SELECT * FROM InputSearch")
    fun getAll():List<InputSearch>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInputSearch(inputSearch: InputSearch)
}