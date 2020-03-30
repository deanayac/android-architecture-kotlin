package com.bootcamp.kotlin.main

import androidx.fragment.app.Fragment

class HomeCommand(private val fragment: Fragment) : OrderCommand {
    override fun execute(changeFragment: (Fragment) -> Unit) {
        changeFragment(fragment)
    }
}
