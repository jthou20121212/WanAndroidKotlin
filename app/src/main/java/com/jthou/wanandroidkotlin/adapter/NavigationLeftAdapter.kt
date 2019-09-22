package com.jthou.wanandroidkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.ItemNavigationLeftBinding
import com.jthou.wanandroidkotlin.event.PositionEvent
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.viewholder.NavigationLeftViewHolder
import org.greenrobot.eventbus.EventBus


/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationLeftAdapter constructor(val data: List<Navigation>) : RecyclerView.Adapter<NavigationLeftViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationLeftViewHolder {
        return NavigationLeftViewHolder(
            ItemNavigationLeftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).listen { position, _ ->
            notifyDataSetChanged(position)

            EventBus.getDefault().post(PositionEvent(position))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NavigationLeftViewHolder, position: Int) {
        holder.bindAdapter(this)
        holder.bind(data[position])
    }

    fun notifyDataSetChanged(position: Int) {
        for ((index, value) in data.withIndex()) {
            value.isSelected = position == index
        }
        notifyDataSetChanged()
    }

}