package com.bootcamp.kotlin.util

/**
 * Created by jhon on 8/04/2020
 */
interface AccountRepository {
    fun saveUserName(userName: String): Boolean
    fun checkIfUserExists(): String
}