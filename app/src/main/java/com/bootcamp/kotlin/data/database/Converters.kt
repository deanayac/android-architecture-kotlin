package com.bootcamp.kotlin.data.database

import androidx.room.TypeConverter
import com.bootcamp.kotlin.data.database.entity.Genre
import com.google.gson.Gson
import timber.log.Timber

class Converters {

    @TypeConverter
    fun toListGenres(json: String?): List<Genre>? {
        return try {
            return json?.let {
                Gson().fromJson(it, Array<Genre>::class.java).toList()
            }
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    @TypeConverter
    fun toGenresData(genres: List<Genre>): String? {
        return try {
            Gson().toJson(genres).toString()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
}