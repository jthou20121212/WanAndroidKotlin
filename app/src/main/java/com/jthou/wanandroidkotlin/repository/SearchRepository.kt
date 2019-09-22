package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository

/**
 * @author jthou
 * @version 1.0.0
 * @date 14-09-2019
 */
class SearchRepository internal constructor(private val dataRepository: DataRepository) {

    fun getHotSearchList() = dataRepository.getHotSearchList()

}
