package com.bootcamp.kotlin.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bootcamp.kotlin.databinding.ActivitySplashBinding
import com.bootcamp.kotlin.ui.main.MainActivity
import com.bootcamp.kotlin.ui.register.RegisterFragment
import com.bootcamp.kotlin.util.startActivity

class SplashActivity : AppCompatActivity(), RegisterFragment.ActionListener {

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]

        sleepScreen()
    }

    private fun updateUi(model: SplashViewModel.UiModel) {
        when (model) {
            is SplashViewModel.UiModel.Navigation ->  navigateToHome()
            is SplashViewModel.UiModel.SleepScreen -> sleepScreen()
        }
    }

    private fun sleepScreen() {
        viewModel.sleepScreen()
    }

    override fun navigateToHome() {
        startActivity<MainActivity>()
        finish()
    }
}