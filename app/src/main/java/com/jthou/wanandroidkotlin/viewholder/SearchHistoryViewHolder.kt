package com.jthou.wanandroidkotlin.viewholder

import android.widget.TextView
import com.jthou.wanandroidkotlin.data.entity.SearchHistory
import com.jthou.wanandroidkotlin.databinding.ItemSearchHistoryBinding

class SearchHistoryViewHolder(viewBinding: ItemSearchHistoryBinding) :
    BaseViewHolder<SearchHistory>(viewBinding) {

    override fun bind(item: SearchHistory) {
        super.bind(item)
        (itemView as? TextView)?.let {
            it.text = item.keyword
        }
    }

}