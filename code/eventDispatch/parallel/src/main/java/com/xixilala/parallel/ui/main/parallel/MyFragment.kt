package com.xixilala.parallel.ui.main.parallel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xixilala.parallel.R
import com.xixilala.parallel.ui.main.MyWebActivity

class MyFragment :Fragment() {

    val parallelViews = ArrayList<View>()

    companion object {

        @JvmStatic
        fun newInstance(layoutId: Int): MyFragment {
            return MyFragment().apply {
                arguments = Bundle().apply {
                    putInt("layout", layoutId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val layoutInflater = MyLayoutInflater(inflater ,activity,this)
        val layoutId = arguments?.getInt("layout")
        layoutId?.let {
            return layoutInflater.inflate(it,null)
        }
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.imageView)?.setOnClickListener { startActivity(Intent(activity, MyWebActivity::class.java)) }
    }
}