package com.movies.data.repository

import com.movies.data.source.SharedPreferencesDataSource

/**
 * Created by jhon on 4/06/2020
 */
class SharedPreferencesRepositoryImpl(private val sharedPreferencesDataSource: SharedPreferencesDataSource)
    : SharedPreferencesRepository{

    override suspend fun saveUserName(userName: String): Boolean {
        return sharedPreferencesDataSource.saveUserName(userName)
    }

    override suspend fun checkIfUserExists(): String {
        return sharedPreferencesDataSource.checkIfUserExists()
    }
}