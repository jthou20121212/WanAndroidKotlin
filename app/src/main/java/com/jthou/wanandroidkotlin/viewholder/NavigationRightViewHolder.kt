package com.jthou.wanandroidkotlin.viewholder

import com.google.android.flexbox.FlexboxLayoutManager
import com.jthou.wanandroidkotlin.adapter.NavigationRightChildAdapter
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.ItemNavigationRightBinding

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationRightViewHolder constructor(private val viewBinding : ItemNavigationRightBinding) : BaseViewHolder<Navigation>(viewBinding) {

    override fun bind(item: Navigation) {
        super.bind(item)
        viewBinding.recyclerView.layoutManager = FlexboxLayoutManager(mContext)
        viewBinding.recyclerView.adapter = NavigationRightChildAdapter(item.articles)
    }

}