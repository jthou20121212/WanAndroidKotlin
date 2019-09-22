package com.jthou.wanandroidkotlin.utils

import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}