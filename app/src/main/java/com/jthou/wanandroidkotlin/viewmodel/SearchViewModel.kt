package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.data.entity.SearchHistory
import com.jthou.wanandroidkotlin.repository.SearchRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 14-09-2019
 */
class SearchViewModel constructor(private val repository: SearchRepository) : ViewModel() {

    fun getHotSearchList() = repository.getHotSearchList()

    fun getSearchHistoryList() = repository.getSearchHistoryList()

    fun insertDataSearchHistory(data: SearchHistory) = repository.insertDataSearchHistory(data)

    fun clearSearchHistory() = repository.clearSearchHistory()

}