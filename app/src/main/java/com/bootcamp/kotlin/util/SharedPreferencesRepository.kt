package com.bootcamp.kotlin.util

/**
 * Created by jhon on 8/04/2020
 */
interface SharedPreferencesRepository {

    fun initSharedPreferences()
    fun checkIfUserExists(): String
}