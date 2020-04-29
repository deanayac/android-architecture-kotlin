package com.bootcamp.kotlin.ui.register

/**
 * Created by jhon on 6/04/2020
 */
interface RegisterContract {

    interface View {
        fun showMessage(message: String)
        fun navigateToHome()
    }

    interface Presenter {
        fun saveUserName(userName: String)
        fun saveToken()
    }
}