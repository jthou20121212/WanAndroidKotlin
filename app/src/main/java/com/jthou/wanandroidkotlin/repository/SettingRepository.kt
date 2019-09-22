package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository

/**
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class SettingRepository internal constructor(private val dataRepository: DataRepository) {

     fun setAutoCache(value: Boolean) = dataRepository.setAutoCache(value)

     fun setNoImageMode(value: Boolean) = dataRepository.setNoImageMode(value)

     fun setNightMode(value: Boolean) = dataRepository.setNightMode(value)

     fun isAutoCache() = dataRepository.isAutoCache()

     fun isNoImageMode() = dataRepository.isNoImageMode()

     fun isNightMode() = dataRepository.isNightMode()

}
