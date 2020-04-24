package com.bootcamp.kotlin.data.database.dao

import androidx.room.*
import com.bootcamp.kotlin.data.database.entity.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun findById(id: Int): Movie

    @Query("SELECT COUNT(id) FROM Movie")
    fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)

    @Query("DELETE FROM Movie")
    fun deleteAll()
}