package com.bootcamp.kotlin.register

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.util.AndroidHelper
import com.bootcamp.kotlin.util.AccountRepository

/**
 * Created by jhon on 6/04/2020
 */
class RegisterPresenter(
    private val view: RegisterContract.View?,
    private val repository: AccountRepository
) : RegisterContract.Presenter {

    override fun saveUserName(userName: String) {
        if (repository.saveUserName(userName)) {
            view?.navigateToHome()
        } else {
            view?.showMessage(AndroidHelper.getString(R.string.error_save_user))
        }
    }

    override fun saveToken() {
        TODO("Not yet implemented")
    }
}