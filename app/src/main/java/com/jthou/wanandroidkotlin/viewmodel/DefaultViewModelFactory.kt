package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class DefaultViewModelFactory(private val viewModel: DefaultViewModel) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}