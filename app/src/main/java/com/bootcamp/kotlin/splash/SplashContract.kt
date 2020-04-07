package com.bootcamp.kotlin.splash

import android.content.SharedPreferences

/**
 * Created by jhon on 6/04/2020
 */
interface SplashContract {

    interface View {
        fun hideSplash()
        fun checkIfUserExists(sharedPreferences: SharedPreferences): String
    }

    interface Presenter {
        fun initView()
        fun sleepScreen()
        fun onDestroy()
    }
}