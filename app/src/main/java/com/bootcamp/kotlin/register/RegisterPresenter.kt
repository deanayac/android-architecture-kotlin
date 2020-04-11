package com.bootcamp.kotlin.register

import com.bootcamp.kotlin.util.LocalRepository

/**
 * Created by jhon on 6/04/2020
 */
class RegisterPresenter(
    private var repository: LocalRepository
): RegisterContract.Presenter {

    override fun initView() {
        repository.initSharedPreferences()
    }

    override fun saveUserName(userName: String) {
        repository.saveUserName(userName)
    }

    override fun saveToken() {
        TODO("Not yet implemented")
    }
}