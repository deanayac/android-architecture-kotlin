package com.bootcamp.kotlin.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bootcamp.kotlin.data.database.entity.InputSearch

@Dao
interface InputSearchDAO {
    @Query("SELECT * FROM InputSearch")
    fun getAll():List<InputSearch>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInputSearch(inputSearch: InputSearch)
}