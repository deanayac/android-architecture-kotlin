package com.bootcamp.kotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)

        if (checkIfUserExists().isEmpty()) {
            showMessage("No se encontro ningún usuario!")
        } else {
            sleepScreen()
        }
    }

    private fun checkIfUserExists() : String {
        val userExists = ""
        return when {
            sharedPreferences.getString("user_name", "").isNullOrEmpty() -> userExists
            else -> {userExists}
        }
    }

    private fun sleepScreen() {
        /****** Create Thread that will sleep for 5 seconds */
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(2 * 1000.toLong())

                    // After 2 seconds redirect to another intent
                    loadFragment(WelcomeFragment())

                    //Remove activity
                    finish()
                } catch (e: Exception) {
                    Log.d("", e.message!!)
                }
            }
        }
        // start thread
        background.start()
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}