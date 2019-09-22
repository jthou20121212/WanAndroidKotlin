package com.jthou.wanandroidkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemNavigationRightChildBinding
import com.jthou.wanandroidkotlin.viewholder.NavigationRightChildViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationRightChildAdapter(val data: List<Article>) : RecyclerView.Adapter<NavigationRightChildViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NavigationRightChildViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationRightChildViewHolder =
        NavigationRightChildViewHolder(
            ItemNavigationRightChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}