package com.bootcamp.kotlin.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bootcamp.kotlin.databinding.ActivitySplashBinding
import com.bootcamp.kotlin.ui.main.MainActivity
import com.bootcamp.kotlin.ui.register.RegisterFragment
import com.bootcamp.kotlin.util.startActivity
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class SplashActivity : AppCompatActivity(), RegisterFragment.ActionListener {

    private val viewModel: SplashViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: SplashViewModel.UiModel) {
        when (model) {
            is SplashViewModel.UiModel.Navigation ->  navigateToHome()
            is SplashViewModel.UiModel.SleepScreen -> viewModel.sleepScreen()
        }
    }


    override fun navigateToHome() {
        startActivity<MainActivity>()
        finish()
    }
}