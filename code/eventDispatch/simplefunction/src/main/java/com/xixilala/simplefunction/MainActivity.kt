package com.xixilala.simplefunction

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.xixilala.simplefunction.fragments.MyVLayoutFragment
import com.xixilala.simplefunction.fragments.SvgMapFragment
import com.xixilala.simplefunction.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tvShareElement).setOnClickListener {
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, findViewById<View>(R.id.iv), "share")
            startActivity(Intent(this, ShareElementActivity::class.java), optionsCompat.toBundle())
        }

        findViewById<View>(R.id.tvSvgChinaMap).setOnClickListener {
//            www.amcharts.com  下载的svg 需要下边的转换一下才能在 android 里用
//            http://inloop.github.io/svg2android/
            SvgMapFragment().show(supportFragmentManager,"map")
        }
        findViewById<View>(R.id.tvVLayout).setOnClickListener {
            MyVLayoutFragment().show(supportFragmentManager,"vlayout")
        }

        findViewById<View>(R.id.tvSearch).setOnClickListener {
            SearchFragment().show(supportFragmentManager,"search")
        }
    }
}