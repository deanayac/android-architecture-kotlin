package com.bootcamp.kotlin.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PosterItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val density = context.resources.displayMetrics.density
        val padding = 8

        outRect.top = (density * padding).toInt()
        outRect.bottom = (density * padding).toInt()
        outRect.right = (density * padding).toInt()
        outRect.left = (density * padding).toInt()
    }
}