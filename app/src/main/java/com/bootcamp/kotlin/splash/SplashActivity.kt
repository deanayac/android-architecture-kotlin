package com.bootcamp.kotlin.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.main.MainActivity
import com.bootcamp.kotlin.register.RegisterFragment
import com.bootcamp.kotlin.util.AccountRepositoryImpl
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.util.startActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View, RegisterFragment.ActionListener {

    private var presenter: SplashPresenter? = null

    private val handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this, AccountRepositoryImpl(this), handler)
        presenter?.initView()

        sleepScreen()
    }

    private fun sleepScreen() {
        presenter?.sleepScreen()
    }

    override fun navigateToHome() {
        startActivity<MainActivity>()
        finish()
    }

    override fun hideSplash() {
        containerSplash.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun showMessage(message: String) {
        applicationContext.showMessage(message)
    }

    override fun showRegister() {
        attachFragment(
            R.id.splashContainer,
            RegisterFragment(),
            getString(R.string.tag_register_fragment)
        )
        hideSplash()
    }
}