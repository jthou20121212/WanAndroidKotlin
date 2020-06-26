package com.jthou.wanandroidkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.blankj.utilcode.util.ActivityUtils
import com.jthou.wanandroidkotlin.activity.ArticleDetailActivity
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemArticleBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.utils.startActivityWithAnimator
import com.jthou.wanandroidkotlin.viewholder.ArticleViewHolder
import com.jthou.wanandroidkotlin.viewholder.BaseViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class ArticleAdapter : PagedListAdapter<Article, BaseViewHolder<Article>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Article> {
        return ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)).listen { position, _ ->
            val article = getItem(position)
            article?.apply {
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

    override fun onBindViewHolder(holder: BaseViewHolder<Article>, position: Int) {
        getItem(position).let {
            holder.bind(getItem(position)!!)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

}