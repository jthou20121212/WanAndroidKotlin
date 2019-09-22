package com.jthou.wanandroidkotlin.viewholder

import android.text.TextUtils
import androidx.core.text.HtmlCompat
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemArticleBinding
import kotlinx.android.synthetic.main.item_article.view.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class ArticleViewHolder(viewBinding: ItemArticleBinding) : BaseViewHolder<Article>(viewBinding) {

    override fun bind(item: Article) {
        super.bind(item)
        itemView.apply {
            tv_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
            tv_chapter_name.text =
                if (TextUtils.isEmpty(item.superChapterName)) item.chapterName else context.getString(
                    R.string.chapter_name,
                    item.superChapterName,
                    item.chapterName
                )
            iv_favorite.setImageResource(if (item.collect) R.drawable.icon_favorite else R.drawable.icon_like_article_not_selected)
            tv_author.text = item.author
            tv_time.text = item.niceDate
        }
    }

}