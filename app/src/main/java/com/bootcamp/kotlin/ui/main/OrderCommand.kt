package com.bootcamp.kotlin.ui.main

import androidx.fragment.app.Fragment

interface OrderCommand {
    fun execute(changeFragment: (Fragment) -> Unit)
}
