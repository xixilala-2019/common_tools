package com.xixilala.lamemp3

import android.app.Application

class LAPP:Application() {

    companion object {
        lateinit var context:LAPP
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}