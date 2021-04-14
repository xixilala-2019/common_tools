package com.xixilala.simplefunction.pojo

import android.graphics.*


class ProviceItem(val path: Path) {

    /**
     * 绘制颜色
     */
    private var drawColor = 0
    fun setDrawColor(drawColor: Int) {
        this.drawColor = drawColor
    }



    fun drawItem(canvas: Canvas, paint: Paint, isSelect: Boolean) {
        if (isSelect) {
            //绘制内部颜色
            paint.clearShadowLayer()
            paint.strokeWidth = 1f
            paint.style = Paint.Style.FILL
            paint.color = -0x10000
            canvas.drawPath(path, paint)
            //绘制边界
            paint.style = Paint.Style.STROKE
            paint.color = -0x2f170c
            canvas.drawPath(path, paint)
        } else {
            paint.strokeWidth = 2f
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL
            paint.setShadowLayer(8f, 0f, 0f, 0xffffff)
            canvas.drawPath(path, paint)
            //绘制边界
            paint.clearShadowLayer()
            paint.color = drawColor
            paint.style = Paint.Style.FILL
            paint.strokeWidth = 2f
            canvas.drawPath(path, paint)
        }
    }

    fun isTouch(x: Float, y: Float): Boolean { //注意注意这块是来判断点击位置的 主要知识点Region
        val rectF = RectF()
        path.computeBounds(rectF, true)
        val region = Region()
        region.setPath(
            path,
            Region(
                rectF.left.toInt(),
                rectF.top.toInt(),
                rectF.right.toInt(),
                rectF.bottom.toInt()
            )
        )
        return region.contains(x.toInt(), y.toInt())
    }

}