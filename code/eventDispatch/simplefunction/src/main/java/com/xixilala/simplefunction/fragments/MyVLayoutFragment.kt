package com.xixilala.simplefunction.fragments

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.*
import com.alibaba.android.vlayout.layout.FixAreaLayoutHelper.FixViewAnimatorHelper
import com.alibaba.android.vlayout.layout.FixLayoutHelper.BOTTOM_LEFT
import com.xixilala.simplefunction.R


class MyVLayoutFragment : BaseFragment() {

    lateinit var rv: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rv = RecyclerView(activity!!).apply {
            setBackgroundColor(Color.WHITE)
            val virtualLayoutManager = VirtualLayoutManager(activity!!)
            layoutManager = virtualLayoutManager

            adapter = DelegateAdapter(virtualLayoutManager).apply {


                activity?.let {
                    addAdapter(TextAdapter(it))

                    addAdapter(ImageAdapter(it))

                    addAdapter(HLinearAdapter(it))
                    addAdapter(GridAdapter(it))
                    addAdapter(FixedAdapter(it))
                    addAdapter(ScrollFixedAdapter(it))
                    addAdapter(FloatAdapter(it))
                    addAdapter(ColumnAdapter(it))
                    addAdapter(StickyAdapter(it))
                    addAdapter(StaggeredAdapter(it))


                    addAdapter(ImageAdapter(it))

                }


            }
        }
        return rv
    }
}

class TextAdapter(val activity: Activity) : DelegateAdapter.Adapter<TextAdapter.H>() {

    override fun onCreateLayoutHelper(): LayoutHelper {
        return LinearLayoutHelper()
    }

    inner class H(tv: TextView) : RecyclerView.ViewHolder(tv)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        return H(TextView(activity).apply { text = "Text is" })
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: H, position: Int) {

    }
}

class ImageAdapter(val activity: Activity) : DelegateAdapter.Adapter<ImageAdapter.H>() {

    override fun onCreateLayoutHelper(): LayoutHelper {
        return LinearLayoutHelper()
    }

    inner class H(tv: ImageView) : RecyclerView.ViewHolder(tv)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        return H(ImageView(activity).apply { setImageResource(R.drawable.ic_launcher_background) })
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: H, position: Int) {

    }
}

class HLinearAdapter(val activity: Activity) : DelegateAdapter.Adapter<HLinearAdapter.H>() {
    inner class H(tv: RecyclerView) : RecyclerView.ViewHolder(tv)
    inner class HTextAdapter(val activity: Activity) : DelegateAdapter.Adapter<HTextAdapter.H>() {

        override fun onCreateLayoutHelper(): LayoutHelper {
            return LinearLayoutHelper()
        }

        inner class H(tv: TextView) : RecyclerView.ViewHolder(tv)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
            return H(TextView(activity).apply {
                text = "HLinear Text is"
                setTextColor(Color.BLACK)
                height = 100
            })
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: H, position: Int) {

        }
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return SingleLayoutHelper()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HLinearAdapter.H {
        val rvH = RecyclerView(activity)
        rvH.setBackgroundColor(Color.CYAN)
        rvH.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)
        val manager = VirtualLayoutManager(activity, OrientationHelper.HORIZONTAL)
        rvH.layoutManager = manager

        rvH.adapter = DelegateAdapter(manager).apply {
            addAdapter(HTextAdapter(activity))
            addAdapter(HTextAdapter(activity))
        }
        return H(rvH)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: H, position: Int) {

    }
}

class Holder(v: View) : RecyclerView.ViewHolder(v)

open abstract class MyDelegrateAdapter : DelegateAdapter.Adapter<Holder>() {
    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}

class GridAdapter(val activity: Activity) : MyDelegrateAdapter() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val ll = LinearLayout(activity)
        ll.orientation = LinearLayout.VERTICAL
        ll.addView(ImageView(activity).apply {
            setImageResource(R.drawable.ic_launcher_background)
            layoutParams = ViewGroup.LayoutParams(100, 100)
        })
        ll.addView(TextView(activity).apply { setText("grid item") })
        return Holder(ll)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return GridLayoutHelper(5)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

    }
}

class FixedAdapter(val activity: Activity) : MyDelegrateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ImageView(activity).apply {
            layoutParams = ViewGroup.LayoutParams(1000, 100)
            setImageResource(R.drawable.ic_launcher_background)
            setBackgroundColor(Color.BLACK)
        })
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return FixLayoutHelper(0, 0).apply {
            setSketchMeasure(true)
            setFixViewAnimatorHelper(object : FixViewAnimatorHelper {
                override fun onGetFixViewAppearAnimator(fixView: View): ViewPropertyAnimator {
                    val height = fixView.measuredHeight
                    fixView.translationY = -height.toFloat()
                    return fixView.animate().translationYBy(height.toFloat()).alpha(1.0f)
                        .setDuration(500)
                }

                override fun onGetFixViewDisappearAnimator(fixView: View): ViewPropertyAnimator {
                    val height = fixView.measuredHeight
                    return fixView.animate().translationYBy(-height.toFloat()).alpha(0.0f)
                        .setDuration(500)
                }
            })
        }
    }

}

class ScrollFixedAdapter(val activity: Activity) : MyDelegrateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ImageView(activity).apply {
            layoutParams = ViewGroup.LayoutParams(1000, 100)
            setImageResource(R.drawable.ic_launcher_background)
            setBackgroundColor(Color.BLACK)
        })
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return ScrollFixLayoutHelper(0, 200).apply {
            setSketchMeasure(false)
            setAlignType(BOTTOM_LEFT)
            setMargin(100, 300, 300, 100)
        }
    }

}

class FloatAdapter(val activity: Activity) : MyDelegrateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ImageView(activity).apply {
            layoutParams = ViewGroup.LayoutParams(150, 150)
            setImageResource(R.drawable.ic_launcher_background)
        })
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return FloatLayoutHelper().apply {
            setDefaultLocation(400, 400)
        }
    }

}

class ColumnAdapter(val activity: Activity) : MyDelegrateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(TextView(activity).apply {
            text = "column"
            width = 100
            height = 100
            setBackgroundColor(Color.RED)
        })
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return ColumnLayoutHelper().apply {
            val weightArray = FloatArray(3)
            weightArray[0] = 1F
            weightArray[1] = 2F
            weightArray[2] = 3F
            setWeights(weightArray)
        }
    }

}

class StickyAdapter(val activity: Activity) : MyDelegrateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(TextView(activity).apply {
            setBackgroundColor(Color.BLUE)
            text = "sticky text"
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)
        })
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return StickyLayoutHelper()
    }

}

class StaggeredAdapter(val activity: Activity) : MyDelegrateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ImageView(activity).apply {
            layoutParams = ViewGroup.LayoutParams(100, 100)
            setBackgroundColor(Color.CYAN)
            setImageResource(R.drawable.ic_launcher_foreground)
        })
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return StaggeredGridLayoutHelper().apply {
            setGap(100)
            lane = 2
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.apply {
            val isOdd = (Math.random() * 100).toInt()/2 == 0
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, if (isOdd) 100 else 200)
        }

    }
}