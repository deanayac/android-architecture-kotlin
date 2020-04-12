package com.bootcamp.kotlin.splash

/**
 * Created by jhon on 6/04/2020
 */
interface SplashContract {

    interface View {
        fun hideSplash()
        fun showMessage(message: String)
        fun showRegister()
        fun navigateToHome()
    }

    interface Presenter {
        fun initView()
        fun sleepScreen()
        fun onDestroy()
    }
}