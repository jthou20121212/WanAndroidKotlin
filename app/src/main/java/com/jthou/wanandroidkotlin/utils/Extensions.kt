package com.jthou.wanandroidkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jthou.wanandroidkotlin.R

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

/*
* 扩展属性 lastChar获取String的最后一个字符
* */
val String.lastChar: Char
    get() = get(length - 1)

/*
* 扩展属性 lastChar获取StringBuilder的最后一个字符
* */
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }

fun <T : Activity> T.startActivityWithAnimator(
    intent: Intent,
    options: Bundle? = null
) {
    ContextCompat.startActivity(this, intent, options)
    overridePendingTransition(R.anim.activity_enter_slide_in, R.anim.activity_enter_slide_out)
}