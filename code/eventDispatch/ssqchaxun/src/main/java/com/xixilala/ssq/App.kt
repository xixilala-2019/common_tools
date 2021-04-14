package com.xixilala.ssq

import android.app.Application
import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.bilibili.magicasakura.utils.ThemeUtils
import com.xixilala.ssq.util.ThemeHelper


class App:Application() , ThemeUtils.switchColor {

    override fun onCreate() {
        super.onCreate()
        ThemeUtils.setSwitchColor(this)
    }

    override fun replaceColorById(context: Context, @ColorRes colorId: Int): Int {
        var colorId = colorId
        if (ThemeHelper.isDefaultTheme(context)) {
            return context.getResources().getColor(colorId)
        }
        val theme = getTheme(context)
        if (theme != null) {
            colorId = getThemeColorId(context, colorId, theme)
        }
        return context.getResources().getColor(colorId)
    }

    override fun replaceColor(context: Context, @ColorInt originColor: Int): Int {
        if (ThemeHelper.isDefaultTheme(context)) {
            return originColor
        }
        val theme = getTheme(context)
        var colorId = -1
        if (theme != null) {
            colorId = getThemeColor(context, originColor, theme)
        }
        return if (colorId != -1) resources.getColor(colorId) else originColor
    }

    private fun getTheme(context: Context): String? {
        if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_STORM) {
            return "blue"
        } else if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_HOPE) {
            return "purple"
        } else if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_WOOD) {
            return "green"
        } else if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_LIGHT) {
            return "green_light"
        } else if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_THUNDER) {
            return "yellow"
        } else if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_SAND) {
            return "orange"
        } else if (ThemeHelper.getTheme(context) === ThemeHelper.CARD_FIREY) {
            return "red"
        }
        return null
    }

    @ColorRes
    private fun getThemeColorId(
        context: Context,
        colorId: Int,
        theme: String
    ): Int {
        when (colorId) {
            R.color.theme_color_primary -> return context.getResources()
                .getIdentifier(theme, "color", packageName)
            R.color.theme_color_primary_dark -> return context.getResources()
                .getIdentifier(theme + "_dark", "color", packageName)
            R.color.theme_color_primary_trans -> return context.getResources()
                .getIdentifier(theme + "_trans", "color", packageName)
        }
        return colorId
    }

    @ColorRes
    private fun getThemeColor(context: Context, color: Int, theme: String): Int {
        when (color) {
            -0x48d67 -> return context.getResources()
                .getIdentifier(theme, "color", packageName)
            -0x47a98f -> return context.getResources()
                .getIdentifier(theme + "_dark", "color", packageName)
            -0x660fb794 -> return context.getResources()
                .getIdentifier(theme + "_trans", "color", packageName)
        }
        return -1
    }

}