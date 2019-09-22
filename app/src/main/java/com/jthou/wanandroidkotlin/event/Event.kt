package com.jthou.wanandroidkotlin.event

import com.jthou.wanandroidkotlin.data.entity.LoginData

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
data class PositionEvent(
    val position: Int
)

class RegisterEvent

data class LoginEvent(
    val loginData: LoginData
)

data class SearchEvent(
    val keyword : String
)