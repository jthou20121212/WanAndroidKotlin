package com.jthou.wanandroidkotlin.data.sp

import androidx.lifecycle.LiveData

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 18-08-2019
 */
interface SpHelper {

    fun isAutoCache() : LiveData<Boolean>

    fun isNoImageMode() : LiveData<Boolean>

    fun isNightMode() : LiveData<Boolean>

    fun setAutoCache(value: Boolean)

    fun setNoImageMode(value: Boolean)

    fun setNightMode(value: Boolean)

}