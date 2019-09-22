package com.jthou.wanandroidkotlin.viewholder

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.ItemNavigationLeftBinding
import kotlinx.android.synthetic.main.item_navigation_left.view.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationLeftViewHolder constructor(viewBinding: ItemNavigationLeftBinding) :
    BaseViewHolder<Navigation>(viewBinding) {

    override fun bind(item: Navigation) {
        itemView.apply {
            tv_sort.text = item.name
            tv_sort.setTextColor(
                if (item.isSelected) ContextCompat.getColor(
                    mContext,
                    R.color.colorPrimary
                ) else ContextCompat.getColor(mContext, R.color.shallow_grey)
            )
            setBackgroundColor(if (item.isSelected) Color.WHITE else ContextCompat.getColor(mContext, R.color.deep_grey))
        }
    }

}