package com.xixilala.eventdispatchdemo

import android.util.Log


object Log {
    private const val TAG = "Dispatcher"
    fun d( str:String) {
        Log.d(TAG, "--- $str")
    }
}