package com.jthou.wanandroidkotlin.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.entity.SearchHistory

/**
 *
 *
 * @author jthou
 * @version 5.6.0
 * @date 18-08-2019
 */
interface DbHelper {

    fun insertDataSearchHistory(data: SearchHistory)

    fun clearSearchHistory()

    fun querySearchHistory(): LiveData<PagedList<SearchHistory>>

}