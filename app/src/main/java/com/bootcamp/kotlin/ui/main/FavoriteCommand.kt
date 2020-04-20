package com.bootcamp.kotlin.ui.main

import androidx.fragment.app.Fragment

class FavoriteCommand(private val fragment: Fragment) : OrderCommand {
    override fun execute(changeFragment: (Fragment) -> Unit) {
        changeFragment(fragment)
    }
}
