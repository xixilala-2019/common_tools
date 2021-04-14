package com.xixilala.parallel.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebActivity : Activity() {

    lateinit var wb: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        wb = WebView(this)
        setContentView(wb)

        val url1 = "https://cb.bing.com"
        wb.settings.apply {
            javaScriptEnabled = true

            domStorageEnabled = true
            setAppCacheEnabled(true)

        }
        wb.webViewClient = WebViewClient()
        wb.loadUrl(url1)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && wb.canGoBack()) {
            wb.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}