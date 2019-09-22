package com.jthou.wanandroidkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jthou.wanandroidkotlin.activity.ArticleDetailActivity
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemProjectBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.viewholder.BaseViewHolder
import com.jthou.wanandroidkotlin.viewholder.ProjectViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
class ProjectAdapter : PagedListAdapter<Article, BaseViewHolder<Article>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Article> {
        return ProjectViewHolder(ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .listen { position, _ ->
                val intent = Intent(parent.context, ArticleDetailActivity::class.java)
                val article = getItem(position)
                article?.apply {
                    intent.putExtra(Constant.ArgumentKey.ARTICLE_LINK, link)
                    intent.putExtra(Constant.ArgumentKey.ARTICLE_TITLE, title)
                    intent.putExtra(Constant.ArgumentKey.ARTICLE_ID, article.id)
                    intent.putExtra(Constant.ArgumentKey.ARTICLE_IS_FAVORITE, article.collect)
                    startActivity(parent.context, intent, null)
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