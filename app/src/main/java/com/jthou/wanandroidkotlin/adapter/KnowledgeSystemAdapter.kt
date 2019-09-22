package com.jthou.wanandroidkotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jthou.wanandroidkotlin.activity.KnowledgeSystemDetailActivity
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem
import com.jthou.wanandroidkotlin.databinding.ItemKnowledgeSystemBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.listen
import com.jthou.wanandroidkotlin.viewholder.KnowledgeSystemViewHolder

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 04-09-2019
 */
class KnowledgeSystemAdapter constructor(val context: Context, val data: List<KnowledgeSystem>) :
    RecyclerView.Adapter<KnowledgeSystemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeSystemViewHolder {
        return KnowledgeSystemViewHolder(
            ItemKnowledgeSystemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
            .listen { position, _ ->
                val intent = Intent(context, KnowledgeSystemDetailActivity::class.java)
                intent.putExtra(Constant.ArgumentKey.IT_KNOWLEDGE_HIERARCHY, data[position])
                context.startActivity(intent)
            }
    }

    override fun getItemCount(): Int = if (data.isNullOrEmpty()) 0 else data.size

    override fun onBindViewHolder(holder: KnowledgeSystemViewHolder, position: Int) {
        holder.bind(data[position])
    }

}