package com.bootcamp.kotlin.ui.register

import com.movies.interactor.GetPreferencesName

/**
 * Created by jhon on 6/04/2020
 */
class RegisterPresenter(
    private val view: RegisterContract.View?,
    private val getPreferencesName: GetPreferencesName
) : RegisterContract.Presenter {

    override fun saveUserName(userName: String) {
        /*if (getSharedPreferences.invokeName(userName)) {
            view?.navigateToHome()
        } else {
            view?.showMessage(AndroidHelper.getString(R.string.error_save_user))
        }*/
    }

    override fun saveToken() {
        TODO("Not yet implemented")
    }
}