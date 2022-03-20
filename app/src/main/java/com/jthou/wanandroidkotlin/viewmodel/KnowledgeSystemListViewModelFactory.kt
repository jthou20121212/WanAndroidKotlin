package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.KnowledgeSystemListRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 22-09-2019
 */
class KnowledgeSystemListViewModelFactory(private val repository : KnowledgeSystemListRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KnowledgeSystemListViewModel(repository) as T
    }

}