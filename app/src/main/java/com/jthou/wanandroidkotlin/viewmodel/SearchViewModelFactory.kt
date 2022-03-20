package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.SearchRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 14-09-2019
 */
class SearchViewModelFactory(private val repository : SearchRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }

}