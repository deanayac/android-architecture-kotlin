package com.bootcamp.kotlin.util

/**
 * Created by jhon on 8/04/2020
 */
interface LocalRepository {

    fun initSharedPreferences()
    fun saveUserName(userName: String)
    fun checkIfUserExists(): String
}