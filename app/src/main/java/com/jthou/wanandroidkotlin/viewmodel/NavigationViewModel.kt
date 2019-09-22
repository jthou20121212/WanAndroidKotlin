package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.repository.MainRepository
import com.jthou.wanandroidkotlin.repository.NavigationRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationViewModel constructor(private val repository: NavigationRepository) : ViewModel() {

    fun getNavigationData() : LiveData<AbstractResponse<List<Navigation>>> = repository.getNavigationData()

}