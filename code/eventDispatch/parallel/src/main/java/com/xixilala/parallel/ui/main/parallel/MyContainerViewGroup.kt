package com.xixilala.parallel.ui.main.parallel

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.nineoldandroids.view.ViewHelper
import com.xixilala.parallel.ui.main.SectionsPagerAdapter
import java.lang.Exception

class MyContainerViewGroup : FrameLayout, ViewPager.OnPageChangeListener {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val fragments = ArrayList<MyFragment>()
    fun setIds(layoutIds:IntArray) {
        fragments.clear()
        fragments.ensureCapacity(layoutIds.size)

        for (i in layoutIds) {
            fragments.add(MyFragment.newInstance(i))
        }

        val viewpager = ViewPager(context)
        viewpager.id = View.generateViewId()
        viewpager.clipChildren = false
        viewpager.adapter = SectionsPagerAdapter(context, (context as AppCompatActivity).supportFragmentManager,fragments)

        viewpager.addOnPageChangeListener(this)

        addView(viewpager)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.d("view" , "parentWidth=$width  movePixels=${positionOffsetPixels}   positionOffset=${positionOffset}")
        var inFragment:MyFragment ?= null
        try {
            inFragment = fragments[position + 1]
        } catch (e:Exception) {
        }
        inFragment?.let {

            val parallelViews = inFragment.parallelViews
            for (view in parallelViews) {

                val parallel = view.getTag() as? Parallel
                if (parallel != null) {
                    val dis = (width - positionOffsetPixels)
                    ViewHelper.setTranslationX( view, dis * parallel.x_in  )
                    ViewHelper.setTranslationY(view, dis * parallel.y_in)
                    ViewHelper.setAlpha(view, positionOffset*parallel.a_in) // 透明度
                }
            }
        }


        var outFragment:MyFragment ?= null
        try {
            outFragment = fragments[position]
        } catch (e:Exception) {
        }
        outFragment?.let {

            val parallelViews = outFragment.parallelViews
            for (view in parallelViews) {

                val parallel = view.tag as? Parallel
                if (parallel != null) {
                    val dis = (0 - positionOffsetPixels)
                    ViewHelper.setTranslationX(view, dis* parallel.x_out)
                    ViewHelper.setTranslationY(view, dis *parallel.y_out)
//                    ViewHelper.setAlpha(view, positionOffset*parallel.a_out)
                }
            }
        }


    }

    override fun onPageSelected(position: Int) {

    }
}