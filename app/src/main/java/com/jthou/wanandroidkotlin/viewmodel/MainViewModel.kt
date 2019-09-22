package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.data.entity.Banner
import com.jthou.wanandroidkotlin.repository.MainRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    fun getArticleList() : LiveData<PagedList<Article>> = repository.getArticleList()

    fun getBannerList() : LiveData<AbstractResponse<List<Banner>>> = repository.getBannerList()

}