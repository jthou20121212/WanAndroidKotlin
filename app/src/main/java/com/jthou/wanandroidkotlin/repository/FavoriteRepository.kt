package com.jthou.wanandroidkotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.Article

/**
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class FavoriteRepository internal constructor(private val dataRepository: DataRepository) {

    fun getFavoriteList() : LiveData<PagedList<Article>> = dataRepository.getFavoriteList()

}
