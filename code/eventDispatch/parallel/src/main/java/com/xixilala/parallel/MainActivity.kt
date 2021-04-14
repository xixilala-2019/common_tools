package com.xixilala.parallel

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.xixilala.parallel.ui.main.SectionsPagerAdapter
import com.xixilala.parallel.ui.main.parallel.MyContainerViewGroup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mt = findViewById<MyContainerViewGroup>(R.id.mt)
        val ids = arrayOf(R.layout.layout0,R.layout.layout1,R.layout.layout2).toIntArray()
        mt.setIds(ids)


    }
}