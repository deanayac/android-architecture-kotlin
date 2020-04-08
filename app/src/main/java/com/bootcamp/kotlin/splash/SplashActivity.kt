package com.bootcamp.kotlin.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.util.SharedPreferencesRepositoryImpl
import com.bootcamp.kotlin.util.attachFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.welcome.RegisterFragment
import com.bootcamp.kotlin.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private var presenter: SplashPresenter? = null

    private val handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(SharedPreferencesRepositoryImpl(this), handler)
        presenter?.initView()

        sleepScreen()
    }

    private fun sleepScreen() {
        GlobalScope.launch(Dispatchers.Main) {
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
                    attachFragment(
                        R.id.splashContainer,
                        WelcomeFragment(),
                        getString(R.string.tag_welcome_fragment)
                    )
                    hideSplash()
                }
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
