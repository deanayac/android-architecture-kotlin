package com.bootcamp.kotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    private fun checkIfUserExists() : String? {
        return sharedPreferences.getString(Constants.USER_NAME, Constants.DEFAULT_STRING)
    }

    private fun sleepScreen() {
        /****** Create Thread that will sleep for 2 seconds */
        Handler().postDelayed({
            if (checkIfUserExists()?.isEmpty()!!) {
                showMessage("No hay usuarios registrados")
                loadFragment(RegisterFragment(), R.id.registerFragment)
            } else {
                loadFragment(WelcomeFragment(), R.id.welcomeFragment)
            }
        }, 2 * 1000)
    }

    private fun loadFragment(fragment: Fragment, fragmentId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (fragmentId) {
            R.id.registerFragment -> {
                fragmentTransaction.add(fragmentId, fragment)
                containerRegister.visibility = View.VISIBLE
            }
            R.id.welcomeFragment -> {
                fragmentTransaction.add(fragmentId, fragment)
                containerWelcome.visibility = View.VISIBLE
            }
        }
        fragmentTransaction.commit()
        containerSplash.visibility = View.GONE
    }
}