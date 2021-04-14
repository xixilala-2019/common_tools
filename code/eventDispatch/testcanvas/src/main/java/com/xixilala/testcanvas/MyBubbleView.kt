package com.xixilala.testcanvas

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.PointFEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import kotlin.math.hypot


const val STATE_DEFAULT = 0
const val STATE_CONNECT = 1
const val STATE_APART   = 2
const val STATE_DISMISS = 3


/****
 * 贝塞尔曲线的使用
 */
class MyBubbleView:View {



    private var state:Int = STATE_DEFAULT
    private val maxDistance = 400F//最大距离，超过这个距离就不是 connect 状态
    private val minDistance = 40F // 最小距离，超过这个才是 connect 状态

    private var fixedBubble:PointF = PointF()
    private var moveableBubble:PointF = PointF()
    private var fixedBubbleRadius = 20F
    private var moveableBubbleRadius = 40F
    private var mDist = 0


    private val bubblePaint:Paint = Paint().apply {
                                                color = Color.RED
        strokeWidth = 5F
                                                style = Paint.Style.FILL
                                            }
    private val textPaint:TextPaint = TextPaint().apply {
        color = Color.WHITE
        textSize = 22F
    }


    private val textRect :Rect = Rect()
    private val text :String = "18"
    private val path = Path()

    private val burstImageArray = arrayOf(R.drawable.bb,R.drawable.bb)



    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init()this.maxDistance = defStyleAttr
    }

    private fun init() {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fixedBubble.apply {
            x = w/2F
            y = h/2F
        }
        moveableBubble.apply {
            x = w/2F
            y = h/2F
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 1.default state 2.connect state 3.apart state 4.dismiss state

        // default one circle and text
        // connect two circle and text
        // apart one circle and text
        // dismiss draw bitmap

        canvas?.let { canvas->




            if (state == STATE_CONNECT) {

                canvas.drawCircle(fixedBubble.x,fixedBubble.y, fixedBubbleRadius, bubblePaint)//固定的圆

                val x = (moveableBubble.x-fixedBubble.x).toDouble()
                val y = (moveableBubble.y-fixedBubble.y).toDouble()

                val sinTheta = y/mDist
                val cosTheta = x/mDist

                //D
                val line0StartX = (fixedBubble.x - fixedBubbleRadius * sinTheta).toFloat()
                val line0StartY = (fixedBubble.y + fixedBubbleRadius * cosTheta).toFloat()

                //C
                val line0EndX   = (moveableBubble.x - moveableBubbleRadius * sinTheta).toFloat()
                val line0EndY   = (moveableBubble.y + moveableBubbleRadius * cosTheta).toFloat()

                //A
                val line1StartX = (fixedBubble.x + fixedBubbleRadius * sinTheta).toFloat()
                val line1StartY = (fixedBubble.y - fixedBubbleRadius * cosTheta).toFloat()

                //B
                val line1EndX   = (moveableBubble.x + sinTheta * moveableBubbleRadius).toFloat()
                val line1EndY   = (moveableBubble.y - cosTheta * moveableBubbleRadius).toFloat()


                val controlPointX = ((moveableBubble.x+fixedBubble.x)/2)
                val controlPointY = ((moveableBubble.y+fixedBubble.y)/2)

                path.reset()

                path.moveTo(line0StartX,line0StartY)
                path.quadTo(controlPointX,controlPointY,line0EndX,line0EndY)

                path.lineTo(line1EndX,line1EndY)
                path.quadTo(controlPointX,controlPointY,line1StartX,line1StartY)
                path.close()

                canvas.drawPath(path,bubblePaint)

            }

            if (state != STATE_DISMISS) {
                canvas.drawCircle(fixedBubble.x,fixedBubble.y, fixedBubbleRadius, bubblePaint)//固定的圆

                canvas.drawCircle(moveableBubble.x,moveableBubble.y, moveableBubbleRadius, bubblePaint) //. 移动的圆
                bubblePaint.getTextBounds(text,0, text.length, textRect)//把文本的尺寸写到矩形里
                canvas.drawText(text, moveableBubble.x-textRect.width(),moveableBubble.y+textRect.height(),textPaint)
            }
            if (state == STATE_DISMISS && currentImageIndex < burstImageArray.size) {
               //消失
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {ev->

            when(ev.action) {
                MotionEvent.ACTION_DOWN->{
                    if (state != STATE_DISMISS) {
                        mDist = hypot((ev.y-fixedBubble.y).toDouble(), (ev.x-fixedBubble.x).toDouble()).toInt()
                        if (mDist < fixedBubbleRadius + maxDistance) {
                            state = STATE_CONNECT
                        } else if (mDist< fixedBubbleRadius +maxDistance) {
                            state = STATE_DEFAULT
                        }
                    }
                }
                MotionEvent.ACTION_MOVE->{
                    if (state != STATE_DEFAULT) {
                        mDist = hypot((ev.y-fixedBubble.y).toDouble(), (ev.x-fixedBubble.x).toDouble()).toInt()
                        moveableBubble.x = ev.x
                        moveableBubble.y = ev.y

                        if (mDist > fixedBubbleRadius + maxDistance) {
                            state = STATE_APART
                        } else if(mDist > minDistance) {
                            state = STATE_CONNECT
                        }


                        invalidate()
                    }
                }

                MotionEvent.ACTION_UP->{


                    if (state == STATE_APART) {



                        // 大爆炸
                        startBubbleBurstAnim()

                    } else if (state == STATE_CONNECT) {
                        //橡皮筋效果

                        startBubbleRestAnim()

                    }
                }
            }

        }
        return true
    }
    private var currentImageIndex = 0
    private fun startBubbleBurstAnim() {

        state = STATE_DISMISS
        val burstAnim = ValueAnimator.ofInt(0, burstImageArray.size)
        burstAnim.duration = 500
        burstAnim.interpolator = LinearInterpolator()
        burstAnim.addUpdateListener {
            currentImageIndex = it.animatedValue as Int
            invalidate()
        }
        burstAnim.start()
    }

    private fun startBubbleRestAnim() {
        val dd = Point()
        val restAnim = ValueAnimator.ofObject(PointFEvaluator(),
                                        PointF(moveableBubble.x,moveableBubble.y),
                                        PointF(fixedBubble.x, fixedBubble.y))
        restAnim.duration = 200
        restAnim.interpolator = OvershootInterpolator(5F)
        restAnim.addUpdateListener {
            moveableBubble = it.animatedValue as PointF
            invalidate()
        }
        restAnim.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                state = STATE_DEFAULT
            }
        })
        restAnim.start()

    }
}

open class Bubble {
    var x:Float = 0F
    var y:Float = 0F
    var r:Float = 0F

}