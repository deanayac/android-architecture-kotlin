package com.bootcamp.kotlin.register

/**
 * Created by jhon on 6/04/2020
 */
interface RegisterContract {

    interface View {
        fun validateInput(userName: String): Boolean?
    }

    interface Presenter {
        fun initView()
        fun saveUserName(userName: String)
        fun saveToken()
    }
}