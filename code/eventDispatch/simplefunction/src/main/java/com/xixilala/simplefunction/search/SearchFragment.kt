package com.xixilala.simplefunction.search

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.labo.kaji.relativepopupwindow.RelativePopupWindow
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition.ALIGN_RIGHT
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition.BELOW
import com.xixilala.simplefunction.R
import com.xixilala.simplefunction.databinding.FragmentSearchBinding
import com.xixilala.simplefunction.fragments.BaseFragment

class SearchFragment :BaseFragment() {

    //https://cn.bing.com/search?q=+搜索指令++插件+++-csdn++
    // -dcloud++-it168++-tencent++-zhihu+-sogou++-bilibili&qs=n&form=QBRE&sp=-1&pq=搜索指令+插件+-csdn+-dcloud+-it168+-tencent+-zhihu+-sogou+-bilibili

    val originUrl = "https://cn.bing.com/search?q=%s"

    lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvChooseEngineName.setOnClickListener {

            RelativePopupWindow().apply {

                contentView = FrameLayout(activity!!).apply {
                    layoutParams = ViewGroup.LayoutParams(500,500)
                    addView(TextView(activity).apply {
                        text= "好含含糊糊和"
                        width = 200
                        height = 300
                        setBackgroundColor(Color.BLACK)
                        setTextColor(Color.WHITE)
                    })
                }

            }.showOnAnchor(binding.ibSearch, RelativePopupWindow.VerticalPosition.ABOVE, RelativePopupWindow.HorizontalPosition.CENTER)
        }

        binding.ibSearch.setOnClickListener {
            goBrowser()
        }
    }


    private fun goBrowser() {
//        val specialItem = (binding.spSpecialSite.selectedItem as String)
//        val removeItem = (binding.spRemoveItem.selectedItem as String)
//        val url = "site:$specialItem ${binding.etSearch.text} -$removeItem"
//        val finalUrl = String.format(originUrl, url)
//        SearchResultFragment.showDialog(activity!! as AppCompatActivity, finalUrl)
    }

}

