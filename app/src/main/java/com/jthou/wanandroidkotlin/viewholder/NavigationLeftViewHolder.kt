package com.jthou.wanandroidkotlin.viewholder

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.ItemNavigationLeftBinding

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationLeftViewHolder constructor(private val viewBinding: ItemNavigationLeftBinding) :
    BaseViewHolder<Navigation>(viewBinding) {

    override fun bind(item: Navigation) {
        super.bind(item)
        viewBinding.tvSort.text = item.name
        viewBinding.tvSort.setTextColor(ContextCompat.getColor(mContext, if (item.isSelected) R.color.colorPrimary else R.color.shallow_grey))
        viewBinding.root.setBackgroundColor(if (item.isSelected) Color.WHITE else ContextCompat.getColor(mContext, R.color.deep_grey))
    }

}