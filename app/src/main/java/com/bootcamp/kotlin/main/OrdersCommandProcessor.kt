package com.bootcamp.kotlin.main

import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.favorites.FavoriteFragment
import com.bootcamp.kotlin.home.HomeFragment

object OrdersCommandProcessor {
    private var queue = HashMap<Int, OrderCommand>()

    fun init() {
        queue[R.id.itemHome] = HomeCommand(HomeFragment.newInstance())
        queue[R.id.itemFavorite] = FavoriteCommand(FavoriteFragment.newInstance())
        queue[R.id.itemExtra] = ExtraCommand(FavoriteFragment.newInstance())
    }

    fun invoke(key: Int, changeFragment: (Fragment) -> Unit) = apply {
        if (queue.containsKey(key)) {
            queue[key]?.execute {
                changeFragment(it)
            }
        }
    }

    fun clear() {
        queue.clear()
    }
}