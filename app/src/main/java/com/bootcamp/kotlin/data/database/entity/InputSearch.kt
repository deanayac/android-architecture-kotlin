package com.bootcamp.kotlin.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class InputSearch(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val description:String
) : Parcelable