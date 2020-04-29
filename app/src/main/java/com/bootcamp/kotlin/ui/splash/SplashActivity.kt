package com.bootcamp.kotlin.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.ActivitySplashBinding
import com.bootcamp.kotlin.ui.main.MainActivity
import com.bootcamp.kotlin.ui.register.RegisterFragment
import com.bootcamp.kotlin.util.AccountRepositoryImpl
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.util.startActivity

class SplashActivity : AppCompatActivity(), SplashContract.View, RegisterFragment.ActionListener {

    private var presenter: SplashPresenter? = null
    private val handler: Handler? = null
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.containerSplash.visibility = View.GONE
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