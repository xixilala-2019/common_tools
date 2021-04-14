package com.xixilala.ssq.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xixilala.ssq.BR
import com.xixilala.ssq.data.model.Dlt
import com.xixilala.ssq.databinding.ItemDltBinding
import com.xixilala.ssq.databinding.ItemSsqBinding

class MainDltAdapter(val list:List<Dlt>) :RecyclerView.Adapter<MainDltAdapter.Holder>(){

    class Holder( val binding:ItemDltBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val binding = DataBindingUtil.inflate<ItemSsqBinding>(LayoutInflater.from(parent.context), R.layout.item_ssq,parent,false)
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDltBinding.inflate(inflater,parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.setVariable(BR.dlt,list[position])
        holder.binding.executePendingBindings()
    }


}