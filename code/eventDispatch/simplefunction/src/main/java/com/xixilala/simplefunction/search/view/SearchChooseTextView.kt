package com.xixilala.simplefunction.search.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.flexbox.FlexboxLayout
import com.xixilala.simplefunction.R

class SearchChooseTextView:AppCompatTextView {
    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        minWidth = 200
        val arrow = resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)
        setCompoundDrawables(null,null,arrow,null)

        setBackgroundColor(Color.CYAN)

        val layoutParams: FlexboxLayout.LayoutParams? = layoutParams as? FlexboxLayout.LayoutParams
        layoutParams?.let {layoutParams->
            val margin = 10
            layoutParams.leftMargin = margin
            layoutParams.rightMargin = margin
            layoutParams.topMargin = margin
            layoutParams.bottomMargin = margin
        }
    }
}