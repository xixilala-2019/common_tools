package com.xixilala.simplefunction.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.PathParser
import com.xixilala.simplefunction.R
import com.xixilala.simplefunction.pojo.ProviceItem
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.InputStream
import java.util.*
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory


class Map : View {
    private val colorArray =
        intArrayOf(-0xdc6429, -0xcf561b, -0x7f340f, -0x1) //各省地图显示的颜色
    private var itemList //各省地图列表 各省地图颜色 与路径
            : List<ProviceItem>? = null
    private var paint //初始化画笔
            : Paint? = null
    private var select //选中的省份
            : ProviceItem? = null
    private var totalRect //中国地图的矩形范围
            : RectF? = null
    private var scale = 1.0f //中国地图的缩放比例

    constructor(context: Context?) : super(context) {
        context?.let {
            init(it)
        }
//        init(context)
    }


    private fun init(context: Context) {

        paint = Paint()
        paint!!.isAntiAlias = true
        itemList = ArrayList<ProviceItem>()
        loadThread.start()
    }

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        //获取当前控件的高度 让地图宽高适配当前控件
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        if (totalRect != null) {
            val mapWidth = totalRect!!.width().toDouble()
            scale = (width / mapWidth).toFloat() //获取控件高度为了让地图能缩放到和控件宽高适配
        }
        setMeasuredDimension(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
    }

    //加载中国地图的路径相对比较耗时，这里开启子线程来加载
    private val loadThread: Thread = object : Thread() {
        override fun run() {
            val inputStream: InputStream? = context?.getResources()?.openRawResource(R.raw.china) //读取地图svg
            val factory = DocumentBuilderFactory.newInstance() //获取DocumentBuilderFactory实例
            var builder: DocumentBuilder? = null
            try {
                builder = factory.newDocumentBuilder()
                val doc: Document = builder.parse(inputStream) //解析svg的输入流
                val rootElement: Element = doc.getDocumentElement()
                val items: NodeList = rootElement.getElementsByTagName("path")
                //获取地图的整个上下左右位置，
                var left = -1f
                var right = -1f
                var top = -1f
                var bottom = -1f
                val list: MutableList<ProviceItem> = ArrayList<ProviceItem>()
                for (i in 0 until items.length) {
                    val element: Element = items.item(i) as Element
                    val pathData: String = element.getAttribute("android:pathData")
                    @SuppressLint("RestrictedApi")
                    val path: Path = PathParser.createPathFromPathData(pathData)
                    val proviceItem = ProviceItem(path) //设置路径
                    proviceItem.setDrawColor(colorArray[i % 4]) //设置颜色
                    //取每个省的上下左右 最后拿出最小或者最大的来充当 总地图的上下左右
                    val rect = RectF()
                    path.computeBounds(rect, true)
                    left = if (left == -1f) rect.left else Math.min(left, rect.left)
                    right = if (right == -1f) rect.right else Math.max(right, rect.right)
                    top = if (top == -1f) rect.top else Math.min(top, rect.top)
                    bottom =
                        if (bottom == -1f) rect.bottom else Math.max(bottom, rect.bottom)
                    list.add(proviceItem)
                }
                itemList = list
                totalRect = RectF(left, top, right, bottom) //设置地图的上下左右位置

                //加载完以后刷新界面
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    requestLayout()
                    invalidate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        handleTouch(event.x / scale, event.y / scale)
        return super.onTouchEvent(event)
    }

    private fun handleTouch(x: Float, y: Float) {
        if (itemList == null) {
            return
        }
        var selectItem: ProviceItem? = null
        for (proviceItem in itemList!!) {
            if (proviceItem.isTouch(x, y)) {
                selectItem = proviceItem
            }
        }
        if (selectItem != null) {
            select = selectItem
            postInvalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint?.let {
            if (itemList != null) {
                canvas.save()
                canvas.scale(scale, scale) //把画布缩放匹配到本控件的宽高
                for (proviceItem in itemList!!) {

                    if (proviceItem !== select) {
                        proviceItem.drawItem(canvas, it, false)
                    } else {
                        proviceItem.drawItem(canvas, it, true)
                    }
                }
            }
        }

    }
}
