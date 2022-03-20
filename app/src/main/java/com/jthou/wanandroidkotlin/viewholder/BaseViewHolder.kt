package com.jthou.wanandroidkotlin.viewholder

import android.content.Context
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
abstract class BaseViewHolder<T> (private val viewBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    var mItem : T? = null

    lateinit var mAdapter : RecyclerView.Adapter<out BaseViewHolder<T>>

    val mContext: Context by lazy {
        viewBinding.root.context
    }

    @CallSuper
    open fun bind(item : T) {
        mItem = item
    }

    fun bindAdapter(adapter: RecyclerView.Adapter<out BaseViewHolder<T>>) {
        mAdapter = adapter
    }

}