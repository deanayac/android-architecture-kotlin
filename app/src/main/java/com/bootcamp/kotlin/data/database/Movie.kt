package com.bootcamp.kotlin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String
)