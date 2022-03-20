package com.jthou.wanandroidkotlin.viewholder

import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem
import com.jthou.wanandroidkotlin.databinding.ItemKnowledgeSystemBinding
import com.jthou.wanandroidkotlin.utils.CommonUtils

/**
 *
 *
 * @author jthou
 * @version 5.6.0
 * @date 04-09-2019
 */
class KnowledgeSystemViewHolder(private val viewBinding: ItemKnowledgeSystemBinding) :
    BaseViewHolder<KnowledgeSystem>(viewBinding) {

    override fun bind(item: KnowledgeSystem) {
        super.bind(item)
        viewBinding.tvTitle.text = item.name
        viewBinding.tvTitle.setTextColor(CommonUtils.randomColor())
        val blank = itemView.context.getString(R.string.blank_three)

        val content = item.children.joinToString(separator = blank) { it.name ?: "" }
        viewBinding.tvContent.text = content
    }

}