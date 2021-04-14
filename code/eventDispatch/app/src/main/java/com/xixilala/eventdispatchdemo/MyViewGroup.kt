package com.xixilala.eventdispatchdemo

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class MyViewGroup :FrameLayout{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("VG dispatchTouchEvent ${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("VG onInterceptTouchEvent ${ev?.action}")
        return true
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("VG onTouchEvent ${ev?.action}")
        return super.onTouchEvent(ev)
    }
}