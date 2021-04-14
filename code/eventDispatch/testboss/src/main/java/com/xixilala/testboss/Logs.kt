package com.xixilala.testboss

import android.util.Log

object Logs {
    const val TAG = "dispatchEventTest"
    fun d(str:String) {
        Log.d(TAG,"-- $str")
    }
}