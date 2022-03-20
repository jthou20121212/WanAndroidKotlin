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
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.databinding.ItemProjectBinding
import com.jthou.wanandroidkotlin.utils.ClipboardUtils
import com.jthou.wanandroidkotlin.utils.Injection
import com.jthou.wanandroidkotlin.utils.ViewClick

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class ProjectViewHolder(private val viewBinding: ItemProjectBinding) : BaseViewHolder<Article>(viewBinding) {

    init {
        ViewClick.clicks(viewBinding.tvInstall).subscribe(object : BaseObserver<Any>() {
            override fun onNext(t: Any) {
                ClipboardUtils.copyText(mItem?.apkLink)
                SnackbarUtils.with(itemView.rootView).setBottomMargin(BarUtils.getNavBarHeight()).setMessage(mContext.getString(R.string.already_copy_to_clipboard)).show()
            }
        })
    }

    override fun bind(item: Article) {
        super.bind(item)
        viewBinding.run {
            if (!Injection.getDataRepository().isNoImageMode().value!!) {
                Glide
                    .with(mContext)
                    .load(item.envelopePic)
                    .centerCrop()
                    .placeholder(ColorDrawable(ContextCompat.getColor(mContext, R.color.image_holder)))
                    .into(ivProject)
            }
            tvTitle.text = item.title
            tvContent.text = item.desc
            tvTime.text = item.niceDate
            tvAuthor.text = item.author
            tvTime.visibility = if (TextUtils.isEmpty(item.apkLink)) View.VISIBLE else View.GONE
            tvAuthor.visibility = if (TextUtils.isEmpty(item.apkLink)) View.VISIBLE else View.GONE
            tvInstall.text = item.apkLink
            tvInstall.visibility = if (TextUtils.isEmpty(item.apkLink)) View.GONE else View.VISIBLE
        }
    }

}