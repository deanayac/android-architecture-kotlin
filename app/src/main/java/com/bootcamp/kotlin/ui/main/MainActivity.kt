package com.bootcamp.kotlin.ui.main

import FavoriteFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.ActivityMainBinding
import com.bootcamp.kotlin.ui.movies.HomeFragment
import com.bootcamp.kotlin.ui.moviedetail.MovieDetailActivity
import com.bootcamp.kotlin.ui.search.SearchFragment
import com.bootcamp.kotlin.util.startActivity

private const val MENU_ITEM = "menu_item"

class MainActivity : AppCompatActivity(),
    HomeFragment.Listener,
    FavoriteFragment.Listener,
    SearchFragment.Listener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()

        val menuSelected = savedInstanceState?.getInt(MENU_ITEM, 0) ?: 0

        if (menuSelected > 0) {
            binding.bottomNavigationView.selectedItemId = menuSelected
        }
    }

    override fun navigateTo(movieId: Int) {
        startActivity<MovieDetailActivity> {
            putExtra(MovieDetailActivity.ARG_MOVIE_ID, movieId)
        }
    }

    private fun setupBottomNavigationView() {
        OrdersCommandProcessor.init(this)
        binding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_navigation)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            OrdersCommandProcessor.invoke(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
        binding.bottomNavigationView.selectedItemId = R.id.itemHome
    }

    override fun onDestroy() {
        OrdersCommandProcessor.clear()
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

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}