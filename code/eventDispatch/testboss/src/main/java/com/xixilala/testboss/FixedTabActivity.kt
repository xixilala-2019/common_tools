package com.xixilala.testboss

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/***
 * 滑动固定 tab 形式
 * header
 * tab
 * Rv
 *
 * 先拦截全部事件，在tab滑动到顶部时，再把事件交给 rv
 *
 */
class FixedTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test0)

        val rv: RecyclerView = findViewById(R.id.rv4)
        rv.adapter = MyAdapter(this)
    }
}


class FixedSampleHeaderBehavior : CoordinatorLayout.Behavior<View> {
    // 界面整体向上滑动，达到列表可滑动的临界点
    private var upReach = false

    // 列表向上滑动后，再向下滑动，达到界面整体可滑动的临界点
    private var downReach = false

    // 列表上一个全部可见的item位置
    private var lastPosition = -1

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    override fun onInterceptTouchEvent(
            parent: CoordinatorLayout,
            child: View,
            ev: MotionEvent
    ): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downReach = false
                upReach = false
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: View,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
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

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        if (target is RecyclerView) {
            // 列表第一个全部可见Item的位置
            val pos =
                    (target.layoutManager as LinearLayoutManager?)!!.findFirstCompletelyVisibleItemPosition()
//            Log.d("scrolling","dy=$dy  pos=$pos   down=$downReach  up=$upReach")
            if (pos == 0 && pos < lastPosition) { //整体下移
                downReach = true
            }

            //首先是整体上移，这时候一直是父控件消耗事件
            //到临界点时， rv开始消耗事件

            // 整体可以滑动，否则RecyclerView消费滑动事件
            if (canScroll(child, dy.toFloat()) && pos == 0) {
                Logs.d("--------  $dy ")
                // dy 当View整体上走为正  下为负
                //假设 tv.height == 100
                // 下边的意思就是  在translationY== -120 dy = 10    -120-10 =

                var finalY = child.translationY - dy //tv将要移动的偏移量
                if (finalY < -child.height) {
                    finalY = -child.height.toFloat()
                    upReach = true //整体上移到达临界点
                } else if (finalY > 0) {
                    finalY = 0f
                }
                child.translationY = finalY
                // 让CoordinatorLayout消费滑动事件
                consumed[1] = dy
            }
            lastPosition = pos
        }
    }

    private fun canScroll(child: View, scrollY: Float): Boolean {
        if (scrollY > 0 // 整体上移
                && child.translationY == -child.height.toFloat() // TextView 刚好在屏幕外
                && !upReach) { //
            return false
        }
        return if (downReach) false else true
    }
}

/***
 * 是否整体滑动
 */
class Ft0Behavior : CoordinatorLayout.Behavior<View> {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private var wholeUp = false
    private var wholeDown = false
    private var lastDirection: Int = 0

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            wholeUp = false
            wholeDown = false
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }


    override fun onMeasureChild(
            parent: CoordinatorLayout,
            child: View,
            parentWidthMeasureSpec: Int,
            widthUsed: Int,
            parentHeightMeasureSpec: Int,
            heightUsed: Int): Boolean
    {

        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }

    override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: View,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
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

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        if (target is RecyclerView && target.layoutManager is LinearLayoutManager) {
            val layoutManager = target.layoutManager as LinearLayoutManager
            val pos = layoutManager.findFirstCompletelyVisibleItemPosition()

            Logs.d("-----  $dy")
            val and = lastDirection shl 31
            val and1 = dy shl 31
            if (and != and1) {
                wholeUp = false
                wholeDown = false
            }
            lastDirection = dy

            if (dy > 0) {
                if (pos == 0) {
                    var tempY = child.translationY - dy

                    if (tempY < -child.height) {
                        tempY = -child.height.toFloat()
                    }
                    if (tempY < 0 && !wholeUp) {
                        consumed[1] = dy
                        wholeUp = true
                    }

                    child.y = tempY

                }
            } else {

                if (pos == 0) {

                    var tempY = child.translationY - dy
                    if (tempY > 0) {
                        tempY = 0F
                    }
                    if (tempY < 0 && !wholeDown) {
                        consumed[1] = dy
                        wholeDown = true
                    }
                    child.y = tempY

                }
            }
        }
    }
}

class Ft1Behavior : CoordinatorLayout.Behavior<View> {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
    ): Boolean {
        return dependency.id == R.id.ivHeader
    }

    override fun onDependentViewChanged(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
    ): Boolean {
        child.y = dependency.translationY + dependency.height
        return true
    }
}


class Ft2Behavior : CoordinatorLayout.Behavior<View> {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
    ): Boolean {
        return dependency.id == R.id.ivHeader
    }

    override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: View,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
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

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        if (300 < child.y && child.y < 600) {
            val ty = child.translationY - dy
            val tty = if (ty < 300) 300F else if (ty > 600) 600F else ty
            child.y = tty
            consumed[1] = dy
        }
    }

    override fun onDependentViewChanged(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
    ): Boolean {

        val sHeight = dependency.y + dependency.height

        child.y = 300F + sHeight

        return true
    }
}
