package com.bootcamp.kotlin.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val backdropPath: String?,
    @ColumnInfo val genres: List<Genre>?,
    @ColumnInfo val originalTitle: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val posterPath: String?,
    @ColumnInfo val releaseDate: String,
    @ColumnInfo val video: Boolean,
    @ColumnInfo val voteAverage: Double
)

data class Genre(val id: Int, val name: String)