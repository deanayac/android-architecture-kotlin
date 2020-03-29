package com.bootcamp.kotlin.main

import androidx.fragment.app.Fragment

interface OrderCommand {
    fun execute(changeFragment: (Fragment) -> Unit)
}
