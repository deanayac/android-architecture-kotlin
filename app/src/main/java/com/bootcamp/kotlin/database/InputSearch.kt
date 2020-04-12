package com.bootcamp.kotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class InputSearch(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val description:String
)