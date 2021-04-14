package com.xixilala.testcanvas

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator

/***
 * 图片碎成磨磨的效果
 */
class SplitView : View {

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    val ballRadius = 1F
    lateinit var paint :Paint
    lateinit var bitmap :Bitmap
    lateinit var animate:ValueAnimator
    val balls = ArrayList<Ball>()
    private fun init() {
        paint = Paint().apply {  }
//        bitmap= BitmapFactory.decodeResource(resources, R.drawable.child)
        bitmap = Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565)
        bitmap.eraseColor(Color.CYAN)

        val width  = bitmap.width
        val height = bitmap.height
        for (i in 0 until width) {
            for (j in 0 until height) {
                balls.add(Ball().apply {
                    this.r = ballRadius
                    this.c = bitmap.getPixel(i,j)
                    this.x = i * 2 * ballRadius + ballRadius
                    this.y = j * 2 * ballRadius + ballRadius

                    this.vx = ((Math.pow(-1.0,Math.ceil(Math.random()*1000))) * Math.random() * 20).toFloat()
                    this.vy = rangeInt(i,j)
//                    Log.d("canvas","------------vx=$vx vy=$vy")
                })
            }
        }

        animate = ValueAnimator.ofFloat(0f, .1F)
        animate.repeatCount = 10
        animate.interpolator = BounceInterpolator().apply {

        }
        var index =0
        animate.addUpdateListener {
            updateBall()
            invalidate()
            Log.d("-----","执行第${index}次")
            index++
        }
    }

    private fun rangeInt(i:Int, j:Int): Float {
        val max = Math.max(i,j)
        val min = Math.min(i,j)
        return (min + Math.ceil(Math.random() * (max-min))).toFloat()
    }

    private fun updateBall() {
        for (ball in balls) {
            ball.x += ball.vx.toInt()
            ball.y += ball.vy.toInt()

            ball.vx += ball.ax
            ball.vy += ball.ay
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(500F,500F)
        for (ball in balls) {
            paint.color = ball.c
            canvas?.drawCircle(ball.x, ball.y, ball.r,paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {

            animate.start()

        }
        return super.onTouchEvent(event)
    }

}