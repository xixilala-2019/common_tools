package com.xixilala.simplefunction.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.xixilala.simplefunction.fragments.BaseFragment


class SearchResultFragment :BaseFragment()  {

    lateinit var webView: WebView

    companion object {
        fun showDialog(appCompatActivity: AppCompatActivity,url:String) {
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    putString("url",url)
                }
            }.show(appCompatActivity.supportFragmentManager,"SearchResultFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        webView = WebView(activity!!)
        return webView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.getString("url")?.let { webView.loadUrl(it) }
//        val session = GeckoSession()
//        val runtime = GeckoRuntime.create(activity!!.applicationContext)
//        session.open(runtime)
//        webView.setSession(session)
//        arguments?.getString("url")?.let {
//            session.loadUri(Uri.parse(it))
//        }

       /* session.progressDelegate =  object : GeckoSession.ProgressDelegate{
            override fun onPageStart(session: GeckoSession, url: String) {
                super.onPageStart(session, url)
                Logger.d("onPageStart $url")
            }

            override fun onPageStop(session: GeckoSession, success: Boolean) {
                super.onPageStop(session, success)
                Logger.d("onPageStop $success")
            }
        }
        session.historyDelegate = object : GeckoSession.HistoryDelegate{
            override fun onHistoryStateChange(
                session: GeckoSession,
                historyList: GeckoSession.HistoryDelegate.HistoryList
            ) {
                super.onHistoryStateChange(session, historyList)
                Logger.d("onHistoryStateChange ${historyList.currentIndex}")
            }

            override fun onVisited(
                session: GeckoSession,
                url: String,
                lastVisitedURL: String?,
                flags: Int
            ): GeckoResult<Boolean>? {
                return super.onVisited(session, url, lastVisitedURL, flags)
                Logger.d("onVisited $url")
            }
        }*/


        webView.apply {

            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
                    val url:String ?= request?.url.toString()
                    Logger.d("shouldInterceptRequest url =$url")
                    url?.let {
                        if (!(url.startsWith("http") ||url.startsWith("https"))) {
                            return null
                        }
                    }

                    return super.shouldInterceptRequest(view, request)
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    Logger.d("shouldOverrideUrlLoading url =$url")
                    url?.let {
                        if (!(url.startsWith("http") ||url.startsWith("https"))) {
                            return true
                        }
                    }
                    return super.shouldOverrideUrlLoading(view, url)
                }
            }
            setOnKeyListener(object : View.OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (webView.canGoBack()) {
                            webView.goBack()
                            return true
                        }
                    }
                    return false
                }

            })
        }
    }

    override fun onDestroy() {
//        webView.releaseSession()
        super.onDestroy()
    }
}