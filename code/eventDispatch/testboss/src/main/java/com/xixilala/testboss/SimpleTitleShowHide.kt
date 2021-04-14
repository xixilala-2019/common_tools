package com.xixilala.testboss

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.NestedScrollingChild
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView

class SimpleTitleShowHide : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)



        val rv = findViewById<RecyclerView>(R.id.my_list)
        rv.adapter = MyAdapter(this)

        rv.post {
            Log.d("sssss","----actionBarHeight = ${actionBar?.height}")
        }

    }
}

  class MyAdapter(val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(TextView(context).apply {
            text = "10000"

            gravity = Gravity.CENTER
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)

        }){

        }
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.itemView as TextView).apply {
            setBackgroundColor(when(position%3) { 0 -> { Color.CYAN} 1->{ Color.LTGRAY} else ->Color.YELLOW})
            text = "position=$position"
        }

    }

}