package com.jthou.wanandroidkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.blankj.utilcode.util.ActivityUtils
import com.jthou.wanandroidkotlin.activity.ArticleDetailActivity
import com.jthou.wanandroidkotlin.activity.SearchListActivity
import com.jthou.wanandroidkotlin.data.entity.SearchHistory
import com.jthou.wanandroidkotlin.databinding.ItemSearchHistoryBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.utils.startActivityWithAnimator
import com.jthou.wanandroidkotlin.viewholder.BaseViewHolder
import com.jthou.wanandroidkotlin.viewholder.SearchHistoryViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
class SearchHistoryAdapter : PagedListAdapter<SearchHistory, BaseViewHolder<SearchHistory>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SearchHistory> {
        return SearchHistoryViewHolder(ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .listen { position, _ ->
                val intent = Intent(parent.context, ArticleDetailActivity::class.java)
                val searchHistory = getItem(position)
                searchHistory?.apply {
                    val intent = Intent(parent.context, SearchListActivity::class.java)
                    intent.putExtra(Constant.ArgumentKey.IT_KEYWORD, searchHistory.keyword)
                    val activity = ActivityUtils.getActivityByContext(parent.context)
                    activity?.startActivityWithAnimator(intent)
                }
            }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<SearchHistory>, position: Int) {
        getItem(position).let {
            holder.bind(getItem(position)!!)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<SearchHistory>() {
            override fun areItemsTheSame(oldItem: SearchHistory, newItem: SearchHistory): Boolean {
                return oldItem.keyword == newItem.keyword
            }

            override fun areContentsTheSame(oldItem: SearchHistory, newItem: SearchHistory): Boolean {
                return oldItem.keyword == newItem.keyword
            }
        }
    }

}