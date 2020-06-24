package com.movies.interactor

import com.movies.data.repository.SharedPreferencesRepository

/**
 * Created by jhon on 4/06/2020
 */
class SetPreferencesName(private val sharedPreferencesRepository: SharedPreferencesRepository) {

    suspend fun invoke(userName: String) = sharedPreferencesRepository.saveUserName(userName)
}