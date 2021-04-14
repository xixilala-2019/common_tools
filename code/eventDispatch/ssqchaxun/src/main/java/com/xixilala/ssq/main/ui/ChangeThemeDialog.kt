package com.xixilala.ssq.main.ui

import android.app.Activity
import android.app.ActivityManager.TaskDescription
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.bilibili.magicasakura.utils.ThemeUtils
import com.bilibili.magicasakura.utils.ThemeUtils.ExtraRefreshable
import com.xixilala.ssq.R
import com.xixilala.ssq.util.ThemeHelper
import com.xixilala.ssq.util.ThemeHelper.CARD_HOPE
import com.xixilala.ssq.util.ThemeHelper.CARD_SAKURA
import com.xixilala.ssq.util.ThemeHelper.CARD_STORM


class ChangeThemeDialog:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parent = LinearLayout(activity)
//        val CARD_SAKURA = 0x1
//        val CARD_HOPE = 0x2
//        val CARD_STORM = 0x3
        val color = intArrayOf(CARD_SAKURA,CARD_HOPE,CARD_STORM)
        val colorStr = arrayOf("红色","蓝色","天蓝")
        for (i in color.indices) {
            parent.addView(TextView(activity).apply {

                text = colorStr[i]
                setOnClickListener {
                    ThemeHelper.setTheme(activity!!, color[i])
                    ThemeUtils.refreshUI(
                        activity,
                        object : ExtraRefreshable {
                            override fun refreshGlobal(activity: Activity) {
                                //for global setting, just do once
                                if (Build.VERSION.SDK_INT >= 21) {
                                    val context= activity as AppCompatActivity
                                    val taskDescription = TaskDescription(
                                        null,
                                        null,
                                        ThemeUtils.getThemeAttrColor(
                                            context,
                                            R.attr.colorPrimary
                                        )
                                    )
                                    context.setTaskDescription(taskDescription)
                                    context.window.statusBarColor = ThemeUtils.getColorById(
                                        context,
                                        R.color.theme_color_primary
                                    )
                                }
                            }

                            override fun refreshSpecificView(view: View) {}
                        }
                    )
                }
            })
        }
        return parent
    }
}