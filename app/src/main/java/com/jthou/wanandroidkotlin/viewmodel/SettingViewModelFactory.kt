package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.SettingRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class SettingViewModelFactory(private val repository : SettingRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(repository) as T
    }

}