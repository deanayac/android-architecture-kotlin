package com.bootcamp.kotlin.welcome

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.util.showMessage
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
        /****** Create Thread that will sleep for 2 seconds */
        handler.postDelayed({
            checkIfUserExists()?.let {
                if (it.isEmpty()) {
                    showMessage("No hay usuarios registrados")
                    R.id.splashContainer.attachFragment(
                        RegisterFragment(),
                        getString(R.string.tag_register_fragment)
                    )
                    hideSplash()
                } else {
                    R.id.splashContainer.attachFragment(
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

    private fun Int.attachFragment(
        view: Fragment,
        tag: String
    ) {
        supportFragmentManager.beginTransaction()
            .replace(this, view, tag)
            .addToBackStack(getString(R.string.tag_register_fragment))
            .commit()
    }

    private fun hideSplash() {
        containerSplash.visibility = View.GONE
    }
}