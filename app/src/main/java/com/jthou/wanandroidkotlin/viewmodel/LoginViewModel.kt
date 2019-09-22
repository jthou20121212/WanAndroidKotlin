package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.repository.LoginRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {

    fun login(username: String, password: String) = repository.login(username, password)

}