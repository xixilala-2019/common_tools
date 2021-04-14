package com.xixilala.eventdispatchdemo

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyChildView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("Child dispatchTouchEvent ${ev?.action}")
        super.dispatchTouchEvent(ev)
        return true
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("Child onTouchEvent ${ev?.action}")
        super.onTouchEvent(ev)
        return true
    }
}