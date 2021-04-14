package com.xixilala.testboss

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.behavior.SwipeDismissBehavior.SWIPE_DIRECTION_ANY

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnDismiss = findViewById<View>(R.id.btnDismiss)
        val dismissBehavior = SwipeDismissBehavior<View>()
        dismissBehavior.setSwipeDirection(SWIPE_DIRECTION_ANY)
        dismissBehavior.setDragDismissDistance(100f)
        (btnDismiss.layoutParams as CoordinatorLayout.LayoutParams) .behavior = dismissBehavior


      
    }

    fun style1(v: View?) {
        startActivity(Intent(this, SimpleTitleShowHide::class.java))
    }

    fun style2(v: View?) {
        startActivity(Intent(this, WholeScrollThenRv::class.java))
    }

    fun style3(v: View?) {
        startActivity(Intent(this, WholeScroll::class.java))
    }

    fun style4(v: View?) {
        startActivity(Intent(this, FixedTabActivity::class.java))
    }

    fun style5(v: View?) {
        startActivity(Intent(this, ScrollShowTitle::class.java))
    }
    fun style6(v: View?) {
        startActivity(Intent(this, TopPartCanScroll::class.java))
    }
}