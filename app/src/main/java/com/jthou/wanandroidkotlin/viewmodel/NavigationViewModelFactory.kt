package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.NavigationRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 07-09-2019
 */
class NavigationViewModelFactory(private val repository : NavigationRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NavigationViewModel(repository) as T
    }

}