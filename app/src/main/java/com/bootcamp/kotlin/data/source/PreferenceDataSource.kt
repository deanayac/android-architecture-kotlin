package com.bootcamp.kotlin.data.source

import android.app.Application
import android.content.SharedPreferences
import com.bootcamp.kotlin.ui.common.Constants
import com.movies.data.source.SharedPreferencesDataSource

/**
 * Created by jhon on 8/04/2020
 */
class PreferenceDataSource(
    application: Application
) : SharedPreferencesDataSource {

    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
    }

    override suspend fun saveUserName(userName: String): Boolean {
        return sharedPreferences.edit().apply {
            putString(Constants.USER_NAME, userName)
        }.commit()
    }

    override suspend fun checkIfUserExists(): String {
        return sharedPreferences.getString(Constants.USER_NAME, Constants.DEFAULT_STRING) ?: Constants.DEFAULT_STRING
    }
}