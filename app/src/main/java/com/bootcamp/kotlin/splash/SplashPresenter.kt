package com.bootcamp.kotlin.splash

import android.os.Handler
import com.bootcamp.kotlin.util.LocalRepositoryImpl

/**
 * Created by jhon on 6/04/2020
 */
class SplashPresenter(
    private val view: SplashContract.View?,
    private val repository: LocalRepositoryImpl,
    private var handler: Handler?
): SplashContract.Presenter {

    override fun initView() {
        handler = Handler()
    }

    override fun sleepScreen() {
        repository.checkIfUserExists().apply {
            if (isEmpty()) {
                view?.showRegister()
                return@apply
            }

            view?.navigateToHome()
        }
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null)
    }
}