package com.jthou.wanandroidkotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.data.entity.Banner

/**
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class MainRepository internal constructor(private val dataRepository: DataRepository) {

    fun getArticleList() : LiveData<PagedList<Article>> = dataRepository.getArticleList()

    fun getBannerList() : LiveData<AbstractResponse<List<Banner>>> = dataRepository.getBannerList()

}
