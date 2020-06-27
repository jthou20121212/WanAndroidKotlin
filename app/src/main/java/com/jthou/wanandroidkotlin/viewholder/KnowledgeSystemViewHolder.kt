package com.jthou.wanandroidkotlin.viewholder

import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem
import com.jthou.wanandroidkotlin.databinding.ItemKnowledgeSystemBinding
import com.jthou.wanandroidkotlin.utils.CommonUtils
import kotlinx.android.synthetic.main.item_knowledge_system.view.*

/**
 *
 *
 * @author jthou
 * @version 5.6.0
 * @date 04-09-2019
 */
class KnowledgeSystemViewHolder(viewBinding: ItemKnowledgeSystemBinding) :
    BaseViewHolder<KnowledgeSystem>(viewBinding) {

    override fun bind(item: KnowledgeSystem) {
        super.bind(item)
        itemView.apply {
            tv_title.text = item.name
            tv_title.setTextColor(CommonUtils.randomColor())
            val blank = itemView.context.getString(R.string.blank_three)

            val content = item.children.joinToString(separator = blank) { it.name ?: "" }
            tv_content.text = content
        }
    }

}