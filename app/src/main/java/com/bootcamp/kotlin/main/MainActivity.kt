package com.bootcamp.kotlin.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.ActivityMainBinding
import com.bootcamp.kotlin.home.HomeFragment
import com.bootcamp.kotlin.movies.Movie

class MainActivity : AppCompatActivity(), HomeFragment.Listener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutMain, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun navigateTo(movie: Movie) {
        //TODO navigate to movieDetail
    }

    private fun setupBottomNavigationView() {
        OrdersCommandProcessor.init()
        binding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_navigation)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            OrdersCommandProcessor.invoke(it.itemId) { fragment ->
                changeFragment(fragment)
            }
            return@setOnNavigationItemSelectedListener true
        }
        binding.bottomNavigationView.selectedItemId = R.id.itemHome
    }

    override fun onDestroy() {
        OrdersCommandProcessor.clear()
        super.onDestroy()
    }
}