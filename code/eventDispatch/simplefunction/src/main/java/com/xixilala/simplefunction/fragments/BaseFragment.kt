package com.xixilala.simplefunction.fragments

import android.R
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

open class BaseFragment:DialogFragment() {
    override fun onResume() {
        super.onResume()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        // 去掉阴影
        val window = dialog.window
        val lp = window!!.attributes
        lp.dimAmount = 0f
        lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        window.attributes = lp
    }
}