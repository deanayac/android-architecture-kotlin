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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)

        GlobalScope.launch(Dispatchers.Main) {
            sleepScreen()
        }
    }

    private fun checkIfUserExists(): String? {
        return sharedPreferences.getString(Constants.USER_NAME, Constants.DEFAULT_STRING)
    }

    private fun sleepScreen() {
        /****** Create Thread that will sleep for 2 seconds */
        Handler().postDelayed({
            checkIfUserExists()?.let {
                if (it.isEmpty()) {
                    showMessage("No hay usuarios registrados")
                    loadFragment(RegisterFragment(), R.id.registerFragment)
                } else {
                    loadFragment(WelcomeFragment(), R.id.welcomeFragment)
                }
            }
        }, 2 * 1000)
    }

    private fun loadFragment(fragment: Fragment, fragmentId: Int) {
        supportFragmentManager.beginTransaction()
            .apply {
                when (fragmentId) {
                    R.id.registerFragment -> {
                        add(fragmentId, fragment)
                        containerRegister.visibility = View.VISIBLE
                    }
                    R.id.welcomeFragment -> {
                        add(fragmentId, fragment)
                        containerWelcome.visibility = View.VISIBLE
                    }
                }
            }.commit()

        containerSplash.visibility = View.GONE
    }
}