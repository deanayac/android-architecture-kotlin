package com.bootcamp.kotlin.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.ActivityMainBinding
import com.bootcamp.kotlin.home.HomeFragment
import com.bootcamp.kotlin.movie.MovieDetailActivity
import com.bootcamp.kotlin.movies.Movie

private const val MENU_ITEM = "menu_item"

class MainActivity : AppCompatActivity(), HomeFragment.Listener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()

        if (savedInstanceState != null) {
            val menuSelected = savedInstanceState.getInt(MENU_ITEM, 0)

            if (menuSelected > 0) {
                binding.bottomNavigationView.selectedItemId = menuSelected
            }
        }
    }

    override fun navigateTo(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, movie)
        startActivity(intent)
    }

    private fun setupBottomNavigationView() {
        `OrdersCommandProcessor`.init(this)
        binding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_navigation)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            `OrdersCommandProcessor`.invoke(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
        binding.bottomNavigationView.selectedItemId = R.id.itemHome
    }

    override fun onDestroy() {
        `OrdersCommandProcessor`.clear()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MENU_ITEM, getSelectedItem())
    }

    private fun getSelectedItem(): Int {
        binding.bottomNavigationView.menu.forEach {
            if (it.isChecked) {
                return it.itemId
            }
        }

        return 0
    }
}
