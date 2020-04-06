package com.bootcamp.kotlin.splash

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.welcome.RegisterFragment
import com.bootcamp.kotlin.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)

        sleepScreen()
    }

    private fun checkIfUserExists(): String? {
        return sharedPreferences.getString(Constants.USER_NAME, Constants.DEFAULT_STRING)
    }

    private fun sleepScreen() {
        handler.postDelayed({
            checkIfUserExists()?.let {
                if (it.isEmpty()) {
                    showMessage("No hay usuarios registrados")
                    attachFragment(
                        R.id.splashContainer,
                        RegisterFragment(),
                        getString(R.string.tag_register_fragment)
                    )
                    hideSplash()
                } else {
                    attachFragment(
                        R.id.splashContainer,
                        WelcomeFragment(),
                        getString(R.string.tag_welcome_fragment)
                    )
                    hideSplash()
                }
            }
        }, Constants.SPLASH_DELAY)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun hideSplash() {
        containerSplash.visibility = View.GONE
    }
}
