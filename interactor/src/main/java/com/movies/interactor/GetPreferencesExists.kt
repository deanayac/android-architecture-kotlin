package com.movies.interactor

import com.movies.data.repository.SharedPreferencesRepository

/**
 * Created by jhon on 4/06/2020
 */
class GetPreferencesExists(private val sharedPreferencesRepository: SharedPreferencesRepository) {

    suspend fun invoke() = sharedPreferencesRepository.checkIfUserExists()
}