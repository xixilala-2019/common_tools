package com.xixilala.simplefunction.search.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.flexbox.FlexboxLayout

class SearchEditText:AppCompatEditText {

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        setBackgroundColor(Color.parseColor("#889900"))

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