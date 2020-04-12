package com.bootcamp.kotlin.util

/**
 * Created by jhon on 8/04/2020
 */
interface LocalRepository {
    fun saveUserName(userName: String): Boolean
    fun checkIfUserExists(): String
}