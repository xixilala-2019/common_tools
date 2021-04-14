package com.xixilala.simplefunction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class ShareElementActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val image = AppCompatImageView(this)
        image.transitionName = "share"
        image.setImageResource(R.mipmap.share_el)
        setContentView(image)
    }
}