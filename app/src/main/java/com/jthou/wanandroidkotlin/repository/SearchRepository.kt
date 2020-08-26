package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.SearchHistory

/**
 * @author jthou
 * @version 1.0.0
 * @date 14-09-2019
 */
class SearchRepository internal constructor(private val dataRepository: DataRepository) {

    fun getHotSearchList() = dataRepository.getHotSearchList()

    fun getSearchHistoryList() = dataRepository.querySearchHistory()

    fun insertDataSearchHistory(data: SearchHistory) = dataRepository.insertDataSearchHistory(data)

    fun clearSearchHistory() = dataRepository.clearSearchHistory()

}
