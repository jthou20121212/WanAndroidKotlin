package com.jthou.wanandroidkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.ItemNavigationRightBinding
import com.jthou.wanandroidkotlin.viewholder.NavigationRightViewHolder


/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationRightAdapter constructor(val data : List<Navigation>) : RecyclerView.Adapter<NavigationRightViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationRightViewHolder {
        return NavigationRightViewHolder(ItemNavigationRightBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: NavigationRightViewHolder, position: Int) {
        holder.bind(data[position])
    }

}