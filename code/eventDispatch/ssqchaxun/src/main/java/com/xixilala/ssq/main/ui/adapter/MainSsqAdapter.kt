package com.xixilala.ssq.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xixilala.ssq.BR
import com.xixilala.ssq.data.model.Ssq
import com.xixilala.ssq.databinding.ItemSsqBinding

class MainSsqAdapter(val list:List<Ssq>) :RecyclerView.Adapter<MainSsqAdapter.Holder>(){

    class Holder( val binding:ItemSsqBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val binding = DataBindingUtil.inflate<ItemSsqBinding>(LayoutInflater.from(parent.context), R.layout.item_ssq,parent,false)
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSsqBinding.inflate(inflater,parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.setVariable(BR.ssq,list[position])
        holder.binding.executePendingBindings()
    }


}