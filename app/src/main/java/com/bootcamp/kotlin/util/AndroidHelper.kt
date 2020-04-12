package com.bootcamp.kotlin.util

import android.content.Context
import com.bootcamp.kotlin.base.Constants

object AndroidHelper {
    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    fun getString(id: Int): String {
        return context?.getString(id) ?: Constants.DEFAULT_STRING
    }
}