package com.bootcamp.kotlin.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.main.MainActivity
import com.bootcamp.kotlin.util.LocalRepositoryImpl
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.util.launchActivity
import com.bootcamp.kotlin.register.RegisterFragment
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private var presenter: SplashPresenter? = null

    private val handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(LocalRepositoryImpl(this), handler)
        presenter?.initView()

        sleepScreen()
    }

    private fun sleepScreen() {
        presenter?.sleepScreen()?.let { result ->
            if (result.isEmpty()) {
                showMessage(getString(R.string.message_not_found_user))
                attachFragment(
                    R.id.splashContainer,
                    RegisterFragment(),
                    getString(R.string.tag_register_fragment)
                )
                hideSplash()
            } else {
                launchActivity<MainActivity> {  }
                hideSplash()
            }
        }
    }

    override fun hideSplash() {
        containerSplash.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}
