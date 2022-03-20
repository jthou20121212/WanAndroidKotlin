package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.SearchListRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 20-09-2019
 */
class SearchListViewModelFactory(private val repository : SearchListRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchListViewModel(repository) as T
    }

}