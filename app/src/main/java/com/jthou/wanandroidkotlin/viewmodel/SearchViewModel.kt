package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
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

}