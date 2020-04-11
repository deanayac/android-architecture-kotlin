package com.bootcamp.kotlin.util

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.base.Constants

/**
 * Created by jhon on 8/04/2020
 */
class LocalRepositoryImpl(private val appCompatActivity: AppCompatActivity) : LocalRepository {

    private val sharedPreferences: SharedPreferences by lazy {
        appCompatActivity.getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
    }

    override fun saveUserName(userName: String): Boolean {
        return sharedPreferences.edit().apply {
            putString(Constants.USER_NAME, userName)
        }.commit()
    }

    override fun checkIfUserExists(): String {
        return sharedPreferences.getString(Constants.USER_NAME, Constants.DEFAULT_STRING) ?: Constants.DEFAULT_STRING
    }
}