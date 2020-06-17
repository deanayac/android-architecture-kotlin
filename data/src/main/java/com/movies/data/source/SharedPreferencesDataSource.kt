package com.movies.data.source

interface SharedPreferencesDataSource {
    suspend fun saveUserName(userName: String): Boolean
    suspend fun checkIfUserExists(): String
}