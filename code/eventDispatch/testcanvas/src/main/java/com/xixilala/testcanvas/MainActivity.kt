package com.xixilala.testcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(CanvasView(this))
//        setContentView(FrameLayout(this).apply {
//            /*addView(SplitView(this@MainActivity).apply {
////                layoutParams = ViewGroup.LayoutParams(300,300)
//            })*/
//            addView(MyBubbleView(this@MainActivity))
//        })
    }
}

class CanvasView : View {
    val paint: Paint = Paint().apply {
        color = Color.RED
        strokeWidth = 10F
        style = Paint.Style.STROKE
    }

    constructor(context: Context?) : super(context)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



        canvas?.let {

//            it.drawRect(100F,100F,500F,500F,paint)
//            it.translate(100F,100F)
//            paint.color = Color.DKGRAY
//            it.drawRect(100F,100F,500F,500F,paint)

//            it.drawRect(100F,100F,500F,500F,paint)
//            it.scale(0.5f,0.5f)
//            paint.color = Color.DKGRAY
//            it.drawRect(100F,100F,500F,500F,paint)
            Log.d("canvas", "-----cw=${canvas.width}   ch=${canvas.height}")
//            paint.color = Color.RED
//            it.drawRect(100F,100F,500F,500F,paint)
//            paint.color = Color.CYAN
//            it.drawRect(200F,200F,600F,600F,paint)
//            paint.color = Color.BLACK
//            it.drawRect(300F,300F,700F,700F,paint)
//            it.scale(0.5f,0.5f,00f,00f)
//            paint.color = Color.YELLOW
//            it.drawRect(100F,100F,500F,500F,paint)
//            paint.color = Color.GREEN
//            it.drawRect(200F,200F,600F,600F,paint)
//            paint.color = Color.BLUE
//            it.drawRect(300F,300F,700F,700F,paint)

            it.translate(100F,100F)
            it.drawRect(0F,0F,500F,500F,paint)
//            it.scale(0.5f,0.5f)
            it.scale(0.5f,0.5f,10F,10F)
            Log.d("canvas", "-----scaled cw=${canvas.width}   ch=${canvas.height}")
//            it.translate(100f,100f)
//            it.scale(.5f,.5f)
//            it.translate(-100f,-100f)

            paint.color = Color.DKGRAY
            it.drawRect(0F,0F,500F,500F,paint)

            it.rotate(135f)

            paint.color = Color.BLUE
            it.drawRect(0F,0F,700F,700F,paint)

            it.rotate(-135F)
            it.clipRect(0F,0F,700F,700F)
            paint.color  = Color.MAGENTA
            it.skew(1F,0F)//倾斜
            it.drawCircle(00F,00F,300F,paint)


        }

    }
}