package com.xixilala.testboss

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class WholeScroll : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling3)

        val rv3: RecyclerView = findViewById(R.id.rv3)
        rv3.adapter = MyAdapter(this)


        val s1:NestedScrollView
        val s2:NestedScrollingChild

    }
}

class S3HeaderBehavior : CoordinatorLayout.Behavior<View> {
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    var max = 0F

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return ViewCompat.SCROLL_AXIS_VERTICAL == axes
    }


    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

        //dy 手指往上拖是正
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        if (max == 0F) {
            max = child.height.toFloat()
        }
        val ty = child.translationY

        if (dy > 0) { //整体往上
            if (ty + child.height > 0) {
                var y = child.translationY - dy.toFloat()
                if (y < -max) {
                    y = -max
                } else if (y > 0) {
                    y = 0F
                }
                child.translationY = y
                consumed[1] = dy
            }

        } else {//整体往下  dy 手指往上拖是正

            if (target is RecyclerView && target.layoutManager is LinearLayoutManager
                && (target.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != 0) {
                return
            }

            var y = child.translationY - dy.toFloat()
            if (y > 0) {
                y = 0F
            }
            child.translationY = y
            consumed[1] = dy
        }
    }
}

/***
 * rv 放在 header 下边
 */
class S3RecyclerViewBehavior : CoordinatorLayout.Behavior<View> {
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.header
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val ty = dependency.height.toFloat() + dependency.translationY

        child.translationY = if (ty < 0) {
            0F
        } else {
            ty
        }
        return true
    }
}
