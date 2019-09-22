package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.repository.LoginRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class LoginViewModelFactory(private val repository : LoginRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}