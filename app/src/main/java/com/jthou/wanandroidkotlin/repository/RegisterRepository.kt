package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository

/**
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class RegisterRepository internal constructor(private val dataRepository: DataRepository) {

    fun register(username: String, password: String, repassword: String) = dataRepository.register(username, password, repassword)

}
