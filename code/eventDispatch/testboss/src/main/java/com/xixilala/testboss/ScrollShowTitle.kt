package com.xixilala.testboss

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.ViewCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/***
 *
 * 从上到下 依次为 title header list
 * 见 a2.gif
 *
 * title 位置是确认的 -y -> 0
 * header 是 从 0 -> -y
 * list 从y -> header高度-title高度
 *
 */
class ScrollShowTitle : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling4)

        val rv = findViewById<RecyclerView>(R.id.my_list)
        rv.adapter = MyAdapter(this)

        findViewById<View>(R.id.header).setOnClickListener {
            Logs.d("点了了IV")
        }


    }
}
const val TITLE_HEIGHT = 300

/***
 * title依赖 header
 */
class TitleBehavior : CoordinatorLayout.Behavior<View> {
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

        val y = abs(dependency.translationY)
        val ty = -(1-y/TITLE_HEIGHT)*child.height
        child.y = if (ty < 0) {
            ty
        } else {
            0F
        }

        return true
    }
}

class HeaderBehavior:CoordinatorLayout.Behavior<View> {
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        Logs.d("滑动轴${axes}")
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {

        return /*if (ViewCompat.isInLayout(child)) {
            true
        }
        else */ super.onInterceptTouchEvent(parent, child, ev)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View, ///
        target: View, ///rv
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        Logs.d("dy=$dy  ")
        if (child.y > -TITLE_HEIGHT && child.y <= 0) {

            val ty = child.y - dy
            child.translationY = if (ty < -TITLE_HEIGHT) {-TITLE_HEIGHT.toFloat()} else {
                if (ty > 0) {
                    0F
                } else {
                    ty
                }
            }
            consumed[1] = dy
        }
        if (dy < 0 ) {
            if (target is RecyclerView && target.layoutManager is LinearLayoutManager) {
                val pos = (target.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                if (pos == 0) {
                    val ty = child.y - dy
                    child.translationY = if (ty < -TITLE_HEIGHT) {-TITLE_HEIGHT.toFloat()} else {
                        if (ty > 0) {
                            0F
                        } else {
                            ty
                        }
                    }
                    consumed[1] = dy
                }
            }

        }
    }
}

/***
 * 列表也依赖header
 */
class ListBehavior :CoordinatorLayout.Behavior<View> {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    private var initY = 0
    private var dependency: View ?= null
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        this.dependency = dependency
        return dependency.id == R.id.header
    }

    override fun onMeasureChild(
        parent: CoordinatorLayout,
        child: View,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ): Boolean {

        //解决下边少显示了三个item
        // rv高度=parent高度-title高度
        val totalHeight = parent.measuredHeight
        val titleHeight = TITLE_HEIGHT
        val height = totalHeight-titleHeight
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            height,
            View.MeasureSpec.AT_MOST
        )
        parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec,heightUsed)
        return true
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val initY = dependency.height
        val ty = initY - (abs(dependency.translationY)/TITLE_HEIGHT) * (dependency.height- TITLE_HEIGHT)

        child.y = if (ty < TITLE_HEIGHT) {
            TITLE_HEIGHT.toFloat()
        } else {
            ty
        }
        return true
    }


}



class NestImageView : androidx.appcompat.widget.AppCompatImageView  {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Logs.d("iv onTouch ${event?.action}")
        return super.dispatchTouchEvent(event)
    }



}