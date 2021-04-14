package com.xixilala.simplefunction.search.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayout

class SearchItemShowTextView:androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        textSize = 14F
        setBackgroundColor(Color.parseColor("#009988"))

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