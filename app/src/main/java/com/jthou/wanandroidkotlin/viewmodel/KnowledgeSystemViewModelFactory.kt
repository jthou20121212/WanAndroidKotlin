package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.KnowledgeSystemRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 04-09-2019
 */
class KnowledgeSystemViewModelFactory(private val repository : KnowledgeSystemRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KnowledgeSystemViewModel(repository) as T
    }

}