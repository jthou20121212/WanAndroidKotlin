package com.jthou.wanandroidkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.jthou.wanandroidkotlin.activity.SearchListActivity
import com.jthou.wanandroidkotlin.data.entity.HotSearch
import com.jthou.wanandroidkotlin.databinding.ItemHotSearchBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.utils.startActivityWithAnimator
import com.jthou.wanandroidkotlin.viewholder.HotSearchViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class HotSearchAdapter constructor(val data: List<HotSearch>) : RecyclerView.Adapter<HotSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HotSearchViewHolder(ItemHotSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .listen { position, _ ->
                val intent = Intent(parent.context, SearchListActivity::class.java)
                intent.putExtra(Constant.ArgumentKey.IT_KEYWORD, data[position].name)
                val activity = ActivityUtils.getActivityByContext(parent.context)
                activity?.startActivityWithAnimator(intent)
            }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HotSearchViewHolder, position: Int) = holder.bind(data[position])

}