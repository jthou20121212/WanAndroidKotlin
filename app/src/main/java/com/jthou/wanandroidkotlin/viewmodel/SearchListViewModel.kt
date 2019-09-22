package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.repository.SearchListRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 20-09-2019
 */
class SearchListViewModel constructor(private val repository: SearchListRepository) : ViewModel() {

    fun getSearchList(keyword: String) = repository.getSearchList(keyword)

}