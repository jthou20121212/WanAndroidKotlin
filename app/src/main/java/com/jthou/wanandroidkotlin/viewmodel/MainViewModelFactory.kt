package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.MainRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class MainViewModelFactory(private val repository : MainRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}