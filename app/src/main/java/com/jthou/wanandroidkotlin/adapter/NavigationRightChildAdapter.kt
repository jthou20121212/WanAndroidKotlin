package com.jthou.wanandroidkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.jthou.wanandroidkotlin.activity.ArticleDetailActivity
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemNavigationRightChildBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.utils.startActivityWithAnimator
import com.jthou.wanandroidkotlin.viewholder.NavigationRightChildViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationRightChildAdapter(val data: List<Article>) : RecyclerView.Adapter<NavigationRightChildViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NavigationRightChildViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationRightChildViewHolder =
        NavigationRightChildViewHolder(
            ItemNavigationRightChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).listen { position, _ ->
            val article = data[position]
            article.apply {
                val intent = Intent(parent.context, ArticleDetailActivity::class.java)
                intent.putExtra(Constant.ArgumentKey.ARTICLE_LINK, link)
                intent.putExtra(Constant.ArgumentKey.ARTICLE_TITLE, title)
                intent.putExtra(Constant.ArgumentKey.ARTICLE_ID, article.id)
                intent.putExtra(Constant.ArgumentKey.ARTICLE_IS_FAVORITE, article.collect)
                val activity = ActivityUtils.getActivityByContext(parent.context)
                activity?.startActivityWithAnimator(intent)
            }
        }
}