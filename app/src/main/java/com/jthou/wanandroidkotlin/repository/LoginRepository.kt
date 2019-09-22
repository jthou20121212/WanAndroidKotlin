package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository

/**
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class LoginRepository internal constructor(private val dataRepository: DataRepository) {

    fun login(username: String, password: String) = dataRepository.login(username, password)

}
