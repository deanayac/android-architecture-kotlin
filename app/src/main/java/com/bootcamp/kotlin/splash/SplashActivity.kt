package com.bootcamp.kotlin.splash

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.welcome.RegisterFragment
import com.bootcamp.kotlin.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private var presenter: SplashPresenter? = null

    private val handler: Handler? = null

    private val sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this, this, sharedPreferences, handler)
        presenter?.initView()

        sleepScreen()
    }

    private fun sleepScreen() {
        presenter?.sleepScreen()
    }

    override fun hideSplash() {
        containerSplash.visibility = View.GONE
    }

    override fun checkIfUserExists(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(Constants.USER_NAME, Constants.DEFAULT_STRING)!!
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}
