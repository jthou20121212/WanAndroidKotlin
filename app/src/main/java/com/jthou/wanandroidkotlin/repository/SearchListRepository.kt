package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository

/**
 * @author jthou
 * @version 1.0.0
 * @date 20-09-2019
 */
class SearchListRepository internal constructor(private val dataRepository: DataRepository) {

    fun getSearchList(keyword: String) = dataRepository.getSearchList(keyword)

}
