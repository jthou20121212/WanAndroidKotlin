package com.jthou.wanandroidkotlin.viewholder

import com.google.android.flexbox.FlexboxLayoutManager
import com.jthou.wanandroidkotlin.adapter.NavigationRightChildAdapter
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.ItemNavigationRightBinding
import kotlinx.android.synthetic.main.item_navigation_right.view.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationRightViewHolder constructor(viewBinding : ItemNavigationRightBinding) : BaseViewHolder<Navigation>(viewBinding) {

    override fun bind(item: Navigation) {
            itemView.apply {
                recyclerView.layoutManager = FlexboxLayoutManager(mContext)
                recyclerView.adapter = NavigationRightChildAdapter(item.articles)
            }
    }

}