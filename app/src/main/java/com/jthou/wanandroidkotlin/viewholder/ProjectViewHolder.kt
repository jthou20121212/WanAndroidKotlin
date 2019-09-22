package com.jthou.wanandroidkotlin.viewholder

import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.bumptech.glide.Glide
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseObserver
import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemProjectBinding
import com.jthou.wanandroidkotlin.utils.ClipboardUtils
import com.jthou.wanandroidkotlin.utils.Injection
import com.jthou.wanandroidkotlin.utils.ViewClick
import kotlinx.android.synthetic.main.item_project.view.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class ProjectViewHolder(viewBinding: ItemProjectBinding) : BaseViewHolder<Article>(viewBinding) {

    init {
        ViewClick.clicks(itemView.tv_install).subscribe(object : BaseObserver<Any>() {
            override fun onNext(t: Any) {
                ClipboardUtils.copyText(mItem?.apkLink)
                SnackbarUtils.with(itemView.rootView).setBottomMargin(BarUtils.getNavBarHeight()).setMessage(mContext.getString(R.string.already_copy_to_clipboard)).show()
            }
        })
    }

    override fun bind(item: Article) {
        super.bind(item)
        itemView.apply {
            if (!Injection.getDataRepository().isNoImageMode().value!!) {
                Glide
                    .with(context)
                    .load(item.envelopePic)
                    .centerCrop()
                    .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.image_holder)))
                    .into(iv_project)
            }
            tv_title.text = item.title
            tv_content.text = item.desc
            tv_time.text = item.niceDate
            tv_author.text = item.author
            tv_time.visibility = if (TextUtils.isEmpty(item.apkLink)) View.VISIBLE else View.GONE
            tv_author.visibility = if (TextUtils.isEmpty(item.apkLink)) View.VISIBLE else View.GONE
            tv_install.text = item.apkLink
            tv_install.visibility = if (TextUtils.isEmpty(item.apkLink)) View.GONE else View.VISIBLE
        }
    }

}