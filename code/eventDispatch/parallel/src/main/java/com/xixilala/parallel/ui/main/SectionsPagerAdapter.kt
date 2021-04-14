package com.xixilala.parallel.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.xixilala.parallel.R
import com.xixilala.parallel.ui.main.parallel.MyFragment


private val layoutId = arrayOf(
    R.layout.layout0,
    R.layout.layout1,
    R.layout.layout2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    private val  fragments: ArrayList<MyFragment>
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }




    override fun getCount(): Int {
        return fragments.size
    }
}