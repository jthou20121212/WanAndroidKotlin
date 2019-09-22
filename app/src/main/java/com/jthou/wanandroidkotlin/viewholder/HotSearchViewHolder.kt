package com.jthou.wanandroidkotlin.viewholder

import android.widget.TextView
import com.jthou.wanandroidkotlin.data.entity.HotSearch
import com.jthou.wanandroidkotlin.databinding.ItemHotSearchBinding
import com.jthou.wanandroidkotlin.utils.CommonUtils

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class HotSearchViewHolder constructor(dataBinding: ItemHotSearchBinding) : BaseViewHolder<HotSearch>(dataBinding) {

    override fun bind(item: HotSearch) {
        super.bind(item)
        val textView = itemView as? TextView ?: return
        textView.text = item.name
        textView.setBackgroundColor(CommonUtils.randomColor())
    }

}