package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.repository.RegisterRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class RegisterViewModel constructor(private val repository: RegisterRepository) : ViewModel() {

    fun register(username: String, password: String, repassword: String) = repository.register(username, password, repassword)

}