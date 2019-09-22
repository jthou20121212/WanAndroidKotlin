package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.repository.SettingRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class SettingViewModel constructor(private val repository: SettingRepository) : ViewModel() {

    fun setAutoCache(value: Boolean) = repository.setAutoCache(value)

    fun setNoImageMode(value: Boolean) = repository.setNoImageMode(value)

    fun setNightMode(value: Boolean) = repository.setNightMode(value)

    fun isAutoCache() = repository.isAutoCache()

    fun isNoImageMode() = repository.isNoImageMode()

    fun isNightMode() = repository.isNightMode()

}