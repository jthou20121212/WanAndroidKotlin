package com.jthou.wanandroidkotlin.viewholder

import android.content.Intent
import androidx.viewpager.widget.ViewPager
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.activity.ArticleDetailActivity
import com.jthou.wanandroidkotlin.data.entity.Banner
import com.jthou.wanandroidkotlin.databinding.ItemBannerBinding
import com.jthou.wanandroidkotlin.utils.Constant
import kotlinx.android.synthetic.main.item_banner.view.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class BannerViewHolder(viewBinder: ItemBannerBinding) : BaseViewHolder<List<Banner>>(viewBinder) {

    override fun bind(item: List<Banner>) {
        itemView.apply {
            viewPager.apply {
                pageMargin = 20
                offscreenPageLimit = 3
                addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        item.apply {
                            itemView.tv_title.text = this[position].title
                            itemView.tv_index.text = mContext.getString(R.string.index_count, position + 1, size)
                        }
                    }

                })
                setOnItemClickListener { position ->
                    item.apply {
                        val index = position % size
                        val intent = Intent(mContext, ArticleDetailActivity::class.java)
                        intent.apply {
                            putExtra(Constant.ArgumentKey.IT_IS_BANNER, true)
                            putExtra(Constant.ArgumentKey.ARTICLE_ID, item[index].id)
                            putExtra(Constant.ArgumentKey.ARTICLE_LINK, item[index].url)
                            putExtra(Constant.ArgumentKey.ARTICLE_TITLE, item[index].title)
                            mContext.startActivity(intent)
                        }
                    }
                }
            }
        }
    }

}