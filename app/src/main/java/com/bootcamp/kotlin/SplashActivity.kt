package com.bootcamp.kotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)

        sleepScreen()
    }

    private fun checkIfUserExists() : String {
        val userExists = ""
        return when {
            sharedPreferences.getString("user_name", "").isNullOrEmpty() -> userExists
            else -> {userExists}
        }
    }

    private fun sleepScreen() {
        /****** Create Thread that will sleep for 2 seconds */
        Handler().postDelayed({
            if (checkIfUserExists().isEmpty()) {
                showMessage("user not exists")
                loadFragment(RegisterFragment())
            } else {
                showMessage("users exists")
                loadFragment(WelcomeFragment())
            }
        }, 2 * 1000)
    }

    private fun loadFragment(fragment: Fragment) {
        activity_container.visibility = View.GONE
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}