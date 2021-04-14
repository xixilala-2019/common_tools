package com.xixilala.parallel.ui.main.parallel

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.xixilala.parallel.R

class MyLayoutInflater : LayoutInflater {
    private var fragment: MyFragment

    val SYSTEM_VIEW_PREFIX = arrayOf("android.view.","android.widget.")
    val attrIds = arrayOf(
        R.attr.x_in,
        R.attr.x_out,
        R.attr.y_in,
        R.attr.y_out,
        R.attr.a_in,
        R.attr.a_out
    ).toIntArray()

    constructor(original: LayoutInflater?, newContext: Context?, fragment: MyFragment) : super(original, newContext) {
        this.fragment = fragment
       factory2 = MyFactory2(original)

        Log.d("inView","--------------f2=$factory2")
//        factory2 = MyFactory2(from(fragment.context))
//        original?.factory2 = factory2


    }

    override fun cloneInContext(newContext: Context?): LayoutInflater {
        return MyLayoutInflater(this, fragment.activity, fragment)
    }


    private fun createView(original: LayoutInflater?,
                           name: String,
                           context: Context,
                           attrs: AttributeSet) : View? {
        try {

//            val view = original?.createView(name, null, attrs) // api 22  会报错  以上不会

            val clazz = Class.forName(name)
            val constructor = clazz.getConstructor(Context::class.java, AttributeSet::class.java)
            constructor.isAccessible = true
            val view = constructor.newInstance(context,attrs) as View


            view?.let {

                val obtainStyledAttributes = context.obtainStyledAttributes(attrs, attrIds)
                if (obtainStyledAttributes != null && obtainStyledAttributes.length() > 0) {
                    val parallel = Parallel (
                        x_in  = obtainStyledAttributes.getFloat(0, 0F),
                        x_out = obtainStyledAttributes.getFloat(1, 0F),
                        y_in  = obtainStyledAttributes.getFloat(2, 0F),
                        y_out = obtainStyledAttributes.getFloat(3, 0F),
                        a_in  = obtainStyledAttributes.getFloat(4, 1F),
                        a_out = obtainStyledAttributes.getFloat(5, 1F)
                    )
                    view.tag = parallel
                    fragment.parallelViews.add(view)
                    obtainStyledAttributes.recycle()
                }
            }
            return view
        } catch (e:Exception ){
//            e.printStackTrace()
            return null
        }
    }

    /***
     * 为了能够拿到那些补充到系统控件的属性
     */
    inner class MyFactory2(val original: LayoutInflater?) :Factory2 {

        override fun onCreateView(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
        ): View? {

            return onCreateView(name, context, attrs)
        }

        override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
            if (name.contains(".")) {
                return createView(original,name, context, attrs)
            } else {
                for (pre in SYSTEM_VIEW_PREFIX) {
                    val view = createView(original,"${pre}${name}",context,attrs)
                    if (view != null) {
                        return view
                    }
                }
            }
            return null
        }
    }
}