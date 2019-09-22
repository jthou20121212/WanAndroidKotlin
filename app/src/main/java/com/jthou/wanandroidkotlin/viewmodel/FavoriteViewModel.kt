package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.repository.FavoriteRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class FavoriteViewModel constructor(private val repository: FavoriteRepository) : ViewModel() {

    fun getFavoriteList() : LiveData<PagedList<Article>> = repository.getFavoriteList()

}