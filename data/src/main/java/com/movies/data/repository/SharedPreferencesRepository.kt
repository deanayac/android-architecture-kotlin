package com.movies.data.repository

/**
 * Created by jhon on 4/06/2020
 */
interface SharedPreferencesRepository {
    suspend fun saveUserName(userName: String): Boolean
    suspend fun checkIfUserExists(): String
}