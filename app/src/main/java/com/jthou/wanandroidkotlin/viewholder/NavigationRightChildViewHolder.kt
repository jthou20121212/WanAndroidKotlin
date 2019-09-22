package com.jthou.wanandroidkotlin.viewholder

import android.widget.TextView
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemNavigationRightChildBinding
import com.jthou.wanandroidkotlin.utils.CommonUtils

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationRightChildViewHolder constructor(viewBinding: ItemNavigationRightChildBinding) :
    BaseViewHolder<Article>(viewBinding) {

    override fun bind(item: Article) {
        val textView = itemView as? TextView ?: return
        textView.text = item.title
        textView.setTextColor(CommonUtils.randomColor())
    }

}