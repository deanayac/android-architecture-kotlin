package com.bootcamp.kotlin.splash

import android.content.SharedPreferences
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.welcome.RegisterFragment
import com.bootcamp.kotlin.welcome.WelcomeFragment

/**
 * Created by jhon on 6/04/2020
 */
class SplashPresenter(
    private val view: SplashContract.View,
    private val appCompatActivity: AppCompatActivity,
    private var sharedPreferences: SharedPreferences?,
    private var handler: Handler?
): SplashContract.Presenter {

    override fun initView() {
        handler = Handler()
        sharedPreferences = appCompatActivity.getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
    }

    override fun sleepScreen() {
        handler?.postDelayed({
            view.checkIfUserExists(this.sharedPreferences!!).let {
                if (it.isEmpty()) {
                    appCompatActivity.showMessage("No hay usuarios registrados")
                    appCompatActivity.attachFragment(
                        R.id.splashContainer,
                        RegisterFragment(),
                        appCompatActivity.getString(R.string.tag_register_fragment)
                    )
                    view.hideSplash()
                } else {
                    appCompatActivity.attachFragment(
                        R.id.splashContainer,
                        WelcomeFragment(),
                        appCompatActivity.getString(R.string.tag_welcome_fragment)
                    )
                    view.hideSplash()
                }
            }
        }, Constants.SPLASH_DELAY)
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null)
    }
}