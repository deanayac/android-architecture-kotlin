package com.bootcamp.kotlin.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R

internal fun AppCompatActivity.attachFragment(
    containerId: Int,
    view: Fragment,
    tag: String
) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, view, tag)
        .addToBackStack(getString(R.string.tag_register_fragment))
        .commit()
}