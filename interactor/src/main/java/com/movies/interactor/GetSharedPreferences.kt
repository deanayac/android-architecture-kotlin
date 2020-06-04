package com.movies.interactor

import com.movies.data.repository.SharedPreferencesRepository

/**
 * Created by jhon on 4/06/2020
 */
class GetSharedPreferences(private val sharedPreferencesRepository: SharedPreferencesRepository) {

    suspend fun invokeName(userName: String) = sharedPreferencesRepository.saveUserName(userName)

    suspend fun invokeExists() = sharedPreferencesRepository.checkIfUserExists()
}