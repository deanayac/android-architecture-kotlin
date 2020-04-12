package com.bootcamp.kotlin.common.ui

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bootcamp.kotlin.R
import kotlinx.android.synthetic.main.textview_expandable.view.*

private const val DEFAULT_MAX_LINES = 3

class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var maxLines: Int = 0
    private var isStateExpanded = false

    init {
        LayoutInflater.from(context).inflate(R.layout.textview_expandable, this, true)
        buttonSeeMore.setOnClickListener(this)

        context.obtainStyledAttributes(
            attrs,
            R.styleable.ExpandableTextView
        ).apply {
            try {
                maxLines = getInt(R.styleable.ExpandableTextView_maxLines, DEFAULT_MAX_LINES)
            } finally {
                recycle()
            }
        }
    }

    fun setData(description: String?) {
        if (description.isNullOrEmpty()) return

        cardView.visibility = View.VISIBLE
        textViewDescription.maxLines = Int.MAX_VALUE
        textViewDescription.text = description

        textViewDescription.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (textViewDescription.lineCount <= maxLines) {
                    buttonSeeMore.visibility = View.GONE
                    return
                }

                initExpandableTextView()
                buttonSeeMore.visibility = View.VISIBLE
                invalidate()
                requestLayout()
                textViewDescription.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onClick(v: View?) {
        val id = v?.id ?: return

        when (id) {
            R.id.buttonSeeMore -> {
                manageStates()
            }
        }
    }

    private fun manageStates() {
        if (!isStateExpanded) {
            expandedExpandableTextView()
        } else {
            initExpandableTextView()
        }
    }

    private fun expandedExpandableTextView() {
        TransitionManager.beginDelayedTransition(contentExpandableTextView, AutoTransition())
        buttonSeeMore.text = context.getText(R.string.see_less)
        textViewDescription.maxLines = Int.MAX_VALUE
        textViewDescription.ellipsize = null
        isStateExpanded = true
    }

    private fun initExpandableTextView() {
        TransitionManager.beginDelayedTransition(contentExpandableTextView, AutoTransition())
        isStateExpanded = false
        textViewDescription.maxLines = maxLines
        textViewDescription.ellipsize = TextUtils.TruncateAt.END
        buttonSeeMore.text = context.getText(R.string.see_more)
    }
}