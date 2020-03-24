package com.bootcamp.kotlin.main

import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.favorites.FavoriteFragment
import com.bootcamp.kotlin.home.HomeFragment

object OrdersCommandProcessor {
    private var queue = HashMap<Int, OrderCommand>()
    private lateinit var activity: AppCompatActivity

    fun init(activity: AppCompatActivity) {
        this.activity = activity
        queue[R.id.itemHome] = HomeCommand(HomeFragment.newInstance())
        queue[R.id.itemFavorites] = FavoriteCommand(FavoriteFragment.newInstance())
        queue[R.id.itemExtra] = ExtraCommand(FavoriteFragment.newInstance())
    }

    fun invoke(key: Int) = apply {
        if (queue.containsKey(key)) {
            queue[key]?.execute {
                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayoutMain, it)
                    addToBackStack(null)
                }.commit()
            }
        }
    }

    fun clear() {
        queue.clear()
    }
}