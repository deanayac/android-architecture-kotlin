package com.bootcamp.kotlin.splash

import android.os.Handler
import com.bootcamp.kotlin.util.SharedPreferencesRepositoryImpl

/**
 * Created by jhon on 6/04/2020
 */
class SplashPresenter(
    private var repository: SharedPreferencesRepositoryImpl,
    private var handler: Handler?
): SplashContract.Presenter {

    override fun initView() {
        handler = Handler()
        repository.initSharedPreferences()
    }

    override fun sleepScreen(): String {
        return repository.checkIfUserExists()
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null)
    }
}