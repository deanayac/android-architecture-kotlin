package com.bootcamp.kotlin.splash

/**
 * Created by jhon on 6/04/2020
 */
interface SplashContract {

    interface View {
        fun hideSplash()
    }

    interface Presenter {
        fun initView()
        fun sleepScreen(): String
        fun onDestroy()
    }
}