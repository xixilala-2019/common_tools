package com.xixilala.eventdispatchdemo

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.mcv).setOnClickListener {
            Log.d("Child onClick")
        }
        /*findViewById<View>(R.id.mpv).setOnClickListener {
            Log.d("Parent onClick")
        }*/
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("MainActivity dispatchTouchEvent ${ev?.action}")
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            onUserInteraction()
        }
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("MainActivity onTouchEvent ${ev?.action}")
        return super.onTouchEvent(ev)
    }
}